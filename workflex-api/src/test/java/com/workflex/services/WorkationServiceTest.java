package com.workflex.services;

import com.workflex.domain.dtos.WorkationDto;
import com.workflex.domain.mappers.WorkationMapper;
import com.workflex.domain.models.Workation;
import com.workflex.repositories.WorkationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class WorkationServiceTest {

    @Mock
    WorkationRepository repository;

    @Mock
    WorkationMapper mapper;

    @InjectMocks
    WorkationService service;

    @Test
    void getAllWorkations_shouldReturnMappedDtos() {
        var entity = new Workation(); // could be empty
        var dto = new WorkationDto();

        when(repository.findAll()).thenReturn(List.of(entity));
        when(mapper.toDtoList(List.of(entity))).thenReturn(List.of(dto));

        List<WorkationDto> result = service.getAllWorkations();

        assertThat(result).hasSize(1);
        assertThat(result).contains(dto);

        verify(repository).findAll();        // called once
        verify(mapper).toDtoList(List.of(entity)); // called once
        verifyNoMoreInteractions(repository, mapper);
    }
}