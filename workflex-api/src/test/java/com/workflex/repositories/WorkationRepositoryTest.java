package com.workflex.repositories;

import com.workflex.domain.enums.RiskLevel;
import com.workflex.domain.models.Workation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.UUID;


@DataJpaTest
class WorkationRepositoryTest {

    @Autowired
    WorkationRepository repository;

    private Workation newWorkation(
            String employee,
            String originCountry,
            LocalDate deletedAt
    ) {
        return Workation.builder()
                .workationId(UUID.randomUUID().toString())
                .employee(employee)
                .originCountry(originCountry)
                .destinationCountry("Spain")
                .startDate(LocalDate.of(2025, 1, 10))
                .endDate(LocalDate.of(2025, 1, 20))
                .workingDays(8)
                .riskLevel(RiskLevel.LOW)
                .deletedAt(deletedAt)
                .build();
    }

    @Test
    void save_shouldPersistAndLoadEntity() {
        Workation saved = repository.save(
                newWorkation("Alice", "Italy", null)
        );

        Workation found = repository
                .findByIdAndDeletedAtIsNull(saved.getId())
                .orElseThrow();

        assertThat(found)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(saved);
    }

    @Test
    void search_shouldExcludeSoftDeletedWorkations() {
        repository.save(newWorkation("Alice", "Italy", LocalDate.now()));

        Page<Workation> page = repository.search(
                "Alice",
                null,
                PageRequest.of(0, 10)
        );

        assertThat(page).isEmpty();
    }

    @Test
    void search_shouldFilterByEmployee_caseInsensitiveAndPartial() {
        repository.save(newWorkation("Alice Johnson", "Italy", null));
        repository.save(newWorkation("Bob", "Italy", null));

        Page<Workation> page = repository.search(
                "alice",
                null,
                PageRequest.of(0, 10)
        );

        assertThat(page.getContent())
                .hasSize(1)
                .first()
                .extracting(Workation::getEmployee)
                .isEqualTo("Alice Johnson");
    }

    @Test
    void search_shouldFilterByOriginCountry() {
        repository.save(newWorkation("Alice", "Italy", null));
        repository.save(newWorkation("Alice", "France", null));

        Page<Workation> page = repository.search(
                "Alice",
                "Italy",
                PageRequest.of(0, 10)
        );

        assertThat(page.getContent()).hasSize(1);
        assertThat(page.getContent().get(0).getOriginCountry()).isEqualTo("Italy");
    }

    @Test
    void search_shouldApplyEmployeeAndCountryTogether() {
        repository.save(newWorkation("Alice", "Italy", null));
        repository.save(newWorkation("Alice", "France", null));

        Page<Workation> page = repository.search(
                "Alice",
                "France",
                PageRequest.of(0, 10)
        );

        assertThat(page.getContent())
                .hasSize(1)
                .first()
                .extracting(Workation::getOriginCountry)
                .isEqualTo("France");
    }


    @Test
    void search_shouldRespectPaginationAndCountCorrectly() {
        repository.save(newWorkation("Alice", "Italy", null));
        repository.save(newWorkation("Alice", "Italy", null));

        Page<Workation> page = repository.search(
                "Alice",
                null,
                PageRequest.of(0, 1)
        );

        assertThat(page.getContent()).hasSize(1);
        assertThat(page.getTotalElements()).isEqualTo(2);
        assertThat(page.getTotalPages()).isEqualTo(2);
    }

    @Test
    void findAllByDeletedAtIsNull_shouldExcludeDeletedRecords() {
        repository.save(newWorkation("Alice", "Italy", null));
        repository.save(newWorkation("Bob", "France", LocalDate.now()));

        Page<Workation> page = repository.findAllByDeletedAtIsNull(
                PageRequest.of(0, 10)
        );

        assertThat(page.getContent()).hasSize(1);
        assertThat(page.getContent().get(0).getEmployee()).isEqualTo("Alice");
    }




}
