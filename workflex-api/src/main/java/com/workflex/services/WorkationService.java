package com.workflex.services;

import com.workflex.domain.dtos.GetWorkation;
import com.workflex.domain.dtos.WorkationDto;
import com.workflex.domain.mappers.WorkationMapper;
import com.workflex.domain.models.Workation;
import com.workflex.repositories.WorkationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkationService {
    private final WorkationRepository repository;
    private final WorkationMapper mapper;

    public List<WorkationDto> getAllWorkations(GetWorkation params) {
        return mapper.toDtoList(
                repository.search(
                        params.getEmployee(),
                        params.getOriginCountry()
                )
        );
    }

    public WorkationDto getWorkationById(Long id) {
        Workation workation = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Workation not found with id: " + id
                ));
        return mapper.toDto(workation);
    }

    public WorkationDto createWorkation(WorkationDto body) {
        return mapper.toDto(
                repository.save(
                        mapper.toEntity(body)
                )
        );
    }
}