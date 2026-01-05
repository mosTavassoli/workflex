package com.workflex.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkationSearchParams {
    private String employee;
    private String originCountry;
    private String destinationCountry;
}
