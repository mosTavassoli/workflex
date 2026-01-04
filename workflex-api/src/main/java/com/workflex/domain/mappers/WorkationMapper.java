package com.workflex.domain.mappers;

import com.workflex.domain.dtos.GetWorkationDto;
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

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    void updateEntityFromDto(
            WorkationDto dto,
            @MappingTarget Workation entity
    );


    GetWorkation toQueryModel(GetWorkationDto dto);
}

