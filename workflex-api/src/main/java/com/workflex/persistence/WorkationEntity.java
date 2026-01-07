package com.workflex.persistence;

import com.workflex.domain.enums.RiskLevel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workations")
public class WorkationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "workation_id", nullable = false, unique = true)
    private String workationId;

    private String employee;

    @Column(name = "origin")
    private String originCountry;

    @Column(name = "destination")
    private String destinationCountry;

    private LocalDate startDate;
    private LocalDate endDate;

    private Integer workingDays;

    @Enumerated(EnumType.STRING)
    private RiskLevel riskLevel;

    @Column(name="deleted_at")
    private LocalDate deletedAt;
}

