package com.workflex.mapper;

import com.workflex.api.dto.WorkationResponse;
import com.workflex.domain.Workation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkationApiMapper  {

    WorkationResponse toResponse(Workation domain);

    List<WorkationResponse> toResponses(List<Workation> domain);
}

