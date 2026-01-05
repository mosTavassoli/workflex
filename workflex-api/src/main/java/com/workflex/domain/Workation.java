package com.workflex.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Workation {

    private String id;
    private String employee;
    private String originCountry;
    private String destinationCountry;
    private LocalDate startDate;
    private LocalDate endDate;

    private Instant createdAt;
    private Instant updatedAt;

    public void changeDestination(String country) {}
    public void extendEndDate(LocalDate newEndDate) {}
    public void cancel() {}
}

