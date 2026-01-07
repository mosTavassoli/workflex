package com.workflex.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Workation {
    private final String workationId;

    private String employee;
    private String originCountry;
    private String destinationCountry;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate deletedAt;
    private Integer workingDays;

    public Workation(
            String workationId,
            String employee,
            String originCountry,
            String destinationCountry,
            LocalDate startDate,
            LocalDate endDate,
            LocalDate deletedAt,
            Integer workingDays
    ) {
        this.workationId = workationId;
        this.employee = employee;
        this.originCountry = originCountry;
        this.destinationCountry = destinationCountry;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deletedAt = deletedAt;
        this.workingDays = workingDays;
    }


    private Workation(
            String workationId,
            String employee,
            String originCountry,
            String destinationCountry,
            LocalDate startDate,
            LocalDate endDate
    ) {
        this.workationId = workationId;
        this.employee = employee;
        this.originCountry = originCountry;
        this.destinationCountry = destinationCountry;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Workation create(
            String employee,
            String originCountry,
            String destinationCountry,
            LocalDate startDate,
            LocalDate endDate
    ) {
        if (employee == null || employee.isBlank()) {
            throw new IllegalArgumentException("Employee is required");
        }
        if (originCountry == null || destinationCountry == null) {
            throw new IllegalArgumentException("Countries are required");
        }
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Dates are required");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }

        return new Workation(
                generateBusinessId(),
                employee,
                originCountry,
                destinationCountry,
                startDate,
                endDate
        );
    }

    private static String generateBusinessId() {
        return "W-" + UUID.randomUUID();
    }
}

