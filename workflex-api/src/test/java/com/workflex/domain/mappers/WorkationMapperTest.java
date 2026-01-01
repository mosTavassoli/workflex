package com.workflex.domain.mappers;


import com.workflex.domain.dtos.WorkationDto;
import com.workflex.domain.models.Workation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class WorkationMapperTest {

    @Autowired
    private WorkationMapper mapper;

    Workation entity = Workation.builder().id(1L).employee("Alice").build();
    WorkationDto dto = WorkationDto.builder().employee("Alice").build();

    @Test
    void shouldMapEntityToDto() {
        WorkationDto dto = mapper.toDto(entity);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getEmployee()).isEqualTo("Alice");
    }

    @Test
    void shouldMapDTOToEntity() {
        Workation entity = mapper.toEntity(dto);

        assertThat(entity.getId()).isNull();
        assertThat(entity.getEmployee()).isEqualTo("Alice");
    }

    @Test
    void shouldUpdateEntityFromDto() {
        // given
        Workation entity = Workation.builder()
                .id(1L)
                .employee("Alice")
                .originCountry("ITALY")
                .build();

        WorkationDto dto = WorkationDto.builder()
                .employee("Bob")          // should update
                .originCountry(null)      // should be ignored
                .build();

        // when
        mapper.updateEntityFromDto(dto, entity);

        // then
        assertThat(entity.getId()).isEqualTo(1L);                // unchanged
        assertThat(entity.getEmployee()).isEqualTo("Bob");       // updated
        assertThat(entity.getOriginCountry()).isEqualTo("ITALY"); // NOT overwritten
    }


}