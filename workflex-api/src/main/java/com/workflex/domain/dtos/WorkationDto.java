package com.workflex.domain.dtos;

import com.workflex.domain.enums.RiskLevel;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkationDto {
    private Long id;

    @NotBlank
    private String workationId;
    private String employee;
    private String originCountry;
    private String destinationCountry;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    private Integer workingDays;
    private RiskLevel riskLevel;
}

