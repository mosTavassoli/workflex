package com.workflex.repositories;

import com.workflex.domain.enums.RiskLevel;
import com.workflex.domain.models.Workation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

@DataJpaTest
class WorkationRepositoryTest {

    @Autowired
    WorkationRepository workationRepository;

    @Test
    void saveAndFindById_shouldReturnSameEntity() {
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


        Workation saved = workationRepository.save(workation);
        Workation found = workationRepository.findById(saved.getId()).orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getEmployee()).isEqualTo("Alice");
        assertThat(found.getDestinationCountry()).isEqualTo("Spain");
        assertThat(found.getRiskLevel()).isEqualTo(RiskLevel.LOW);

    }
}