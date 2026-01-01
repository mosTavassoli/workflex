package com.workflex.services;

import com.workflex.domain.dtos.GetWorkationDto;
import com.workflex.domain.dtos.WorkationDto;
import com.workflex.domain.mappers.WorkationMapper;
import com.workflex.domain.models.Workation;
import com.workflex.repositories.WorkationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import static com.workflex.repositories.specifications.WorkationSpecifications.*;
import org.springframework.data.jpa.domain.Specification;



@Service
@Transactional
@RequiredArgsConstructor
public class WorkationServiceImpl implements WorkationService {

    private final WorkationRepository repository;
    private final WorkationMapper mapper;

    @Override
    public Page<WorkationDto> getAllWorkations(
            GetWorkationDto params,
            Pageable pageable
    ) {
        Specification<Workation> spec = notDeleted()
                .and(employeeContains(params.getEmployee()))
                .and(originCountryEquals(params.getOriginCountry()));

        Page<Workation> page = repository.findAll(spec, pageable);

        return page.map(mapper::toDto);
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
