package com.workflex.domain.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.workflex.domain.enums.RiskLevel;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "workations")
public class Workation {

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

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    private Integer workingDays;

    @Enumerated(EnumType.STRING)
    private RiskLevel riskLevel;

    @Column(name="deleted_at")
    private LocalDate deletedAt;

    public void markDeleted() {
        if (this.deletedAt != null) {
            throw new IllegalStateException("Workation already deleted");
        }
        this.deletedAt = LocalDate.now();
    }

    public boolean isDeleted() {
        return this.deletedAt != null;
    }
}

