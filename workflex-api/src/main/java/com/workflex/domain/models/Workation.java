package com.workflex.domain.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.workflex.domain.enums.RiskLevel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "workations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Workation {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private String id; // CSV: workationId

    private String employee;

    @Column(name = "origin")
    private String originCountry;

    @Column(name = "destination")
    private String destinationCountry;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    private Integer workingDays;

    @Enumerated(EnumType.STRING)
    private RiskLevel riskLevel;
}

