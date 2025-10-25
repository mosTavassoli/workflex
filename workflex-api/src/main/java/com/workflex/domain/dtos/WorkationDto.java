package com.workflex.domain.dtos;

import com.workflex.domain.enums.RiskLevel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class WorkationDto {
    private String id;
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

