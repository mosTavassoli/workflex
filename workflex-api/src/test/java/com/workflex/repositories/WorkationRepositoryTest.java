package com.workflex.repositories;

import com.workflex.domain.enums.RiskLevel;
import com.workflex.domain.models.Workation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
class WorkationRepositoryTest {

    @Autowired
    WorkationRepository workationRepository;

    Workation workation = Workation.builder()
            .workationId("W1")
            .employee("Alice")
            .originCountry("Italy")
            .destinationCountry("Spain")
            .startDate(LocalDate.of(2025, 1, 10))
            .endDate(LocalDate.of(2025, 1, 20))
            .workingDays(8)
            .riskLevel(RiskLevel.LOW)
            .build();

    @Test
    void save_shouldPersistAndLoadWorkationCorrectly() {
        Workation saved = workationRepository.save(workation);

        assertThat(saved.getId()).isNotNull();

        Workation found = workationRepository.findById(saved.getId())
                .orElseThrow();

        assertThat(found)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(workation);
    }

    @Test
    void shouldReturnListOfWorkationByGivenEmployee() {
        // arrange
        workationRepository.save(workation);

        // act
        List<Workation> result = workationRepository.search("Alice", null);

        // assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0))
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(workation);
    }
}
