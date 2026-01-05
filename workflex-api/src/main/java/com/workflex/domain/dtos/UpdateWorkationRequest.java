package com.workflex.domain.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateWorkationRequest {
    @NotBlank
    private String workationId;

    private String employee;
    private String originCountry;
    private String destinationCountry;
}
