package com.workflex.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class WorkationTest {

    @Test
    void create_shouldCreateWorkation_whenInputIsValid() {
        // arrange
        LocalDate start = LocalDate.of(2026, 1, 1);
        LocalDate end = LocalDate.of(2026, 1, 10);

        // act
        Workation workation = Workation.create(
                "john",
                "ITALY",
                "GERMANY",
                start,
                end
        );

        // assert
        assertThat(workation).isNotNull();
        assertThat(workation.getWorkationId()).startsWith("W-");
        assertThat(workation.getEmployee()).isEqualTo("john");
        assertThat(workation.getOriginCountry()).isEqualTo("ITALY");
        assertThat(workation.getDestinationCountry()).isEqualTo("GERMANY");
        assertThat(workation.getStartDate()).isEqualTo(start);
        assertThat(workation.getEndDate()).isEqualTo(end);
    }

    @Test
    void create_shouldThrowException_whenEmployeeIsNull() {
        assertThatThrownBy(() ->
                Workation.create(
                        null,
                        "ITALY",
                        "GERMANY",
                        LocalDate.now(),
                        LocalDate.now().plusDays(1)
                )
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Employee is required");
    }

    @Test
    void create_shouldThrowException_whenEmployeeIsBlank() {
        assertThatThrownBy(() ->
                Workation.create(
                        "   ",
                        "ITALY",
                        "GERMANY",
                        LocalDate.now(),
                        LocalDate.now().plusDays(1)
                )
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Employee is required");
    }

    @Test
    void create_shouldThrowException_whenCountryIsNull() {
        assertThatThrownBy(() ->
                Workation.create(
                        "john",
                        null,
                        "GERMANY",
                        LocalDate.now(),
                        LocalDate.now().plusDays(1)
                )
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Countries are required");
    }

    @Test
    void create_shouldThrowException_whenDatesAreNull() {
        assertThatThrownBy(() ->
                Workation.create(
                        "john",
                        "ITALY",
                        "GERMANY",
                        null,
                        null
                )
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Dates are required");
    }

    @Test
    void create_shouldThrowException_whenEndDateIsBeforeStartDate() {
        assertThatThrownBy(() ->
                Workation.create(
                        "john",
                        "ITALY",
                        "GERMANY",
                        LocalDate.of(2026, 1, 10),
                        LocalDate.of(2026, 1, 1)
                )
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("End date cannot be before start date");
    }
}
