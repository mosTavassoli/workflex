package com.workflex.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.UUID;


@DataJpaTest
class WorkationRepositoryTest {

    @Autowired
    private WorkationRepository repository;

    private WorkationEntity create(String employee, String originCountry) {
        WorkationEntity w = new WorkationEntity();

        w.setWorkationId(UUID.randomUUID().toString());
        w.setEmployee(employee);
        w.setOriginCountry(originCountry);
        w.setStartDate(LocalDate.of(2025, 1, 1));
        w.setEndDate(LocalDate.of(2025, 1, 10));
        w.setDeletedAt(null);

        return w;
    }

    private final Pageable pageable = PageRequest.of(0, 10);


    @Test
    void search_shouldReturnPagedWorkations() {
        repository.save(create(
                "john",
                "Italy"
        ));

        repository.save(create(
                "alice",
                "Germany"
        ));


        Page<WorkationEntity> page = repository.search(
                "john",
                null,
                pageable
        );

        assertThat(page.getContent())
                .hasSize(1)
                .first()
                .extracting(WorkationEntity::getEmployee)
                .isEqualTo("john");

    }

    @Test
    void search_shouldExcludeSoftDeletedWorkations() {
        repository.save(
                create(
                        "alice",
                        "Germany"
                )
        );

        WorkationEntity deleted = create("alice", "Italy");
        deleted.setDeletedAt(LocalDate.now());

        repository.save(deleted);


        Page<WorkationEntity> response = repository.search(
                "alice",
                null,
                pageable
        );

        assertThat(response.getContent()).hasSize(1);
        assertThat(response.getContent().get(0).getDeletedAt()).isNull();
    }

    @Test
    void search_shouldFilterByEmployee_caseInsensitiveAndPartial() {
        repository.save(
                create(
                        "ALICE",
                        "Italy"
                )
        );

        Page<WorkationEntity> response = repository.search(
                "alice",
                null,
                pageable
        );

        assertThat(response.getContent())
                .hasSize(1)
                .first()
                .extracting(WorkationEntity::getEmployee)
                .isEqualTo("ALICE");
    }

    @Test
    void search_shouldFilterByOriginCountry_caseInsensitive() {
        repository.save(create("john", "Italy"));

        Page<WorkationEntity> response = repository.search(
                "JOHN",
                "ITAly",
                pageable
        );

        assertThat(response.getContent())
                .hasSize(1)
                .first()
                .extracting(WorkationEntity::getOriginCountry)
                .isEqualTo("Italy");
    }


}