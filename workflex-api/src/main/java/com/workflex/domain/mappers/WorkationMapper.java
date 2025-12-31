package com.workflex.domain.mappers;

import com.workflex.domain.dtos.WorkationDto;
import com.workflex.domain.models.Workation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface  WorkationMapper {
    WorkationMapper INSTANCE = Mappers.getMapper(WorkationMapper.class);

    WorkationDto toDto(Workation entity);
    Workation toEntity(WorkationDto dto);

    List<WorkationDto> toDtoList(List<Workation> entities);

    void updateEntityFromDto(WorkationDto dto, @MappingTarget Workation entity);
}
