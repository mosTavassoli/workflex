package com.workflex.domain.mappers;


import com.workflex.domain.dtos.WorkationDto;
import com.workflex.domain.models.Workation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class WorkationMapperTest {

    private final WorkationMapper mapper = WorkationMapper.INSTANCE;

    @Test
    void shouldMapEntityToDto() {
        Workation entity = new Workation();
        entity.setId(1L);
        entity.setEmployee("Alice");

        WorkationDto dto = mapper.toDto(entity);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getEmployee()).isEqualTo("Alice");
    }

    @Test
    void shouldMapDTOToEntity() {
        WorkationDto dto = new WorkationDto();
        dto.setId(2L);
        dto.setEmployee("Alice");

        Workation entity = mapper.toEntity(dto);

        assertThat(entity.getId()).isEqualTo(2L);
        assertThat(entity.getEmployee()).isEqualTo("Alice");
    }

}