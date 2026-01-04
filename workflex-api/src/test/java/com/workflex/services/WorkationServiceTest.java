package com.workflex.services;

import com.workflex.domain.dtos.GetWorkationDto;
import com.workflex.domain.dtos.WorkationDto;
import com.workflex.domain.mappers.WorkationMapper;
import com.workflex.domain.models.Workation;
import com.workflex.repositories.WorkationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class WorkationServiceTest {

    @Mock
    WorkationRepository repository;

    WorkationMapper mapper =
            Mappers.getMapper(WorkationMapper.class);

    WorkationServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new WorkationServiceImpl(repository, mapper);
    }

    @Test
    void getAllWorkations_shouldReturnMappedDtos() {
        PageRequest pageable = PageRequest.of(0, 10);

        var entity = Workation.builder()
                .id(1L)
                .workationId("10")
                .originCountry("ITALY")
                .build();

        Page<Workation> page = new PageImpl<>(
                List.of(entity),
                pageable, 1
        );

        when(repository
                .search(any(), any(), any())
        ).thenReturn(page);

        GetWorkationDto params = new GetWorkationDto();

        Page<WorkationDto> result = service.getAllWorkations(params, pageable);

        assertThat(result)
                .hasSize(1)
                .first()
                .satisfies(dto -> {
                    assertThat(dto.getId()).isEqualTo(1L);
                    assertThat(dto.getWorkationId()).isEqualTo("10");
                    assertThat(dto.getOriginCountry()).isEqualTo("ITALY");
                });

        verify(repository).search(any(), any(), any());
    }

    @Test
    void shouldReturnWorkationByGivenValidId() {
        var entity = Workation.builder().id(1L)
                .workationId("10")
                .originCountry("ITALY")
                .deletedAt(null)
                .build();

        when(repository.findByIdAndDeletedAtIsNull(1L)).thenReturn(Optional.of(entity));


        WorkationDto result = service.getWorkationById(1L);

        assertThat(result)
                .satisfies(dto -> {
                    assertThat(dto.getId()).isEqualTo(1L);
                    assertThat(dto.getWorkationId()).isEqualTo("10");
                    assertThat(dto.getOriginCountry()).isEqualTo("ITALY");
                });

        verify(repository).findByIdAndDeletedAtIsNull(1L);

    }

    @Test
    void shouldThrowExceptionWhenWorkationDoesNotExist() {
        // given
        when(repository.findByIdAndDeletedAtIsNull(99L)).thenReturn(Optional.empty());

        // when / then
        assertThatThrownBy(() -> service.getWorkationById(99L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Workation not found with id: 99");

        verify(repository).findByIdAndDeletedAtIsNull(99L);
    }

    @Test
    void shouldCreateWorkation() {
        WorkationDto dto = WorkationDto.builder()
                .workationId("10")
                .originCountry("ITALY")
                .build();

        Workation savedEntity = Workation.builder()
                .id(2L)
                .workationId("10")
                .originCountry("ITALY")
                .build();

        when(repository.save(any(Workation.class)))
                .thenReturn(savedEntity);

        WorkationDto result = service.createWorkation(dto);

        assertThat(result)
                .satisfies(res -> {
                    assertThat(res.getId()).isEqualTo(2L);
                    assertThat(res.getWorkationId()).isEqualTo("10");
                    assertThat(res.getOriginCountry()).isEqualTo("ITALY");
                });

        verify(repository).save(any(Workation.class));

    }

    @Test
    void shouldReturnWorkationGivenEmployee() {
        var entity = Workation.builder()
                .id(1L)
                .employee("JOHN")
                .workationId("10")
                .originCountry("ITALY")
                .build();

        GetWorkationDto params = GetWorkationDto
                .builder()
                .employee("john")
                .build();

        PageRequest pageable = PageRequest.of(0, 10);
        Page<Workation> page = new PageImpl<>(List.of(entity), pageable, 1);

        when(repository.search(any(), any(), any()))
                .thenReturn(page);

        Page<WorkationDto> result = service.getAllWorkations(params, pageable);

        assertThat(result)
                .hasSize(1)
                .first()
                .satisfies(res -> {
                    assertThat(res.getId()).isEqualTo(1L);
                    assertThat(res.getEmployee()).isEqualTo("JOHN");
                });

    }

}