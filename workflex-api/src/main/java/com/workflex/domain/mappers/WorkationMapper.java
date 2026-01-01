package com.workflex.domain.mappers;

import com.workflex.domain.dtos.WorkationDto;
import com.workflex.domain.models.Workation;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface WorkationMapper {

    // -------- READ mappings --------

    WorkationDto toDto(Workation entity);

    List<WorkationDto> toDtoList(List<Workation> entities);

    // -------- CREATE mapping --------

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
//    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "updatedAt", ignore = true)
    Workation toEntity(WorkationDto dto);

    // -------- UPDATE mapping --------

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
//    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(
            WorkationDto dto,
            @MappingTarget Workation entity
    );
}

