package com.workflex.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkationResponse {
    private String id;
    private String employee;
    private String originCountry;
    private String destinationCountry;
    private LocalDate startDate;
    private LocalDate endDate;
}
