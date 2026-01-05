package com.workflex.mapper;

import com.workflex.domain.Workation;
import com.workflex.persistence.WorkationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkationEntityMapper {

    Workation toDomain(WorkationEntity entity);

    WorkationEntity toEntity(Workation domain);
}

