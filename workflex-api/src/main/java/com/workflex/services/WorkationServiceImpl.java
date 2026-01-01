package com.workflex.services;

import com.workflex.domain.dtos.GetWorkation;
import com.workflex.domain.dtos.WorkationDto;
import com.workflex.domain.mappers.WorkationMapper;
import com.workflex.domain.models.Workation;
import com.workflex.repositories.WorkationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Service
@Transactional
public class WorkationServiceImpl implements WorkationService {

    private final WorkationRepository repository;
    private final WorkationMapper mapper;

    public WorkationServiceImpl(WorkationRepository repository, WorkationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<WorkationDto> getAllWorkations(GetWorkation params, Pageable pageable) {
        return repository.search(
                params.getEmployee(),
                params.getOriginCountry(),
                pageable

        ).map(mapper::toDto);
    }

    @Override
    public WorkationDto getWorkationById(Long id) {
        Workation workation = repository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Workation not found with id: " + id
                ));
        return mapper.toDto(workation);
    }

    @Override
    public WorkationDto createWorkation(WorkationDto body) {
        return mapper.toDto(
                repository.save(
                        mapper.toEntity(body)
                )
        );
    }

    @Override
    public WorkationDto updateWorkation(Long id, WorkationDto body) {
        Workation entity = repository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Workation not found with id: " + id));

        mapper.updateEntityFromDto(body, entity);
        return mapper.toDto(entity);
    }

    public void deleteWorkation(Long id) {
        Workation workation = repository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Workation not found with id: " + id));

        workation.markDeleted();
        repository.save(workation);
    }
}
