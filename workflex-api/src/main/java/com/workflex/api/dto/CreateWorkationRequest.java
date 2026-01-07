package com.workflex.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateWorkationRequest {

    @NotBlank
    private String employee;

    @NotBlank
    private String originCountry;

    @NotBlank
    private String destinationCountry;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}

