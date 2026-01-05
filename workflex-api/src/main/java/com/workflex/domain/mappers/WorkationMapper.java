package com.workflex.domain.mappers;

import com.workflex.domain.dtos.CreateWorkationRequest;
import com.workflex.domain.dtos.GetWorkationDto;
import com.workflex.domain.dtos.UpdateWorkationRequest;
import com.workflex.domain.dtos.WorkationDto;
import com.workflex.domain.models.GetWorkation;
import com.workflex.domain.models.Workation;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface WorkationMapper {

    WorkationDto toDto(Workation entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    Workation toEntity(WorkationDto dto);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    void updateEntityFromDto(
            WorkationDto dto,
            @MappingTarget Workation entity
    );

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startDate", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    @Mapping(target = "workingDays", ignore = true)
    @Mapping(target = "riskLevel", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    void updateEntityFromRequest(
            UpdateWorkationRequest request,
            @MappingTarget Workation entity
    );


    GetWorkation toQueryModel(GetWorkationDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "workingDays", ignore = true)
    @Mapping(target = "riskLevel", ignore = true)
    Workation toEntity(CreateWorkationRequest request);
}

