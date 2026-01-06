package com.workflex.service;

import com.workflex.api.dto.CreateWorkationRequest;
import com.workflex.api.dto.UpdateWorkationRequest;
import com.workflex.api.dto.WorkationSearchParams;

import com.workflex.domain.Workation;
import com.workflex.mapper.WorkationEntityMapper;
import com.workflex.persistence.WorkationEntity;
import com.workflex.persistence.WorkationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkationServiceImpl implements WorkationService {

    private final WorkationRepository repository;
    private final WorkationEntityMapper mapper;

    @Override
    public Workation create(CreateWorkationRequest body) {

        // 1️⃣ Create DOMAIN first (rules enforced)
        Workation domain = Workation.create(
                body.getEmployee(),
                body.getOriginCountry(),
                body.getDestinationCountry(),
                body.getStartDate(),
                body.getEndDate()
        );

        // 2️⃣ Map domain → entity
        WorkationEntity entity = mapper.toEntity(domain);

        // 3️⃣ Persist
        WorkationEntity saved = repository.save(entity);

        return mapper.toDomain(saved);
    }

    @Override
    public Workation getById(Long id) {
        WorkationEntity entity = repository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new EntityNotFoundException("Workation not found"));

        return mapper.toDomain(entity);
    }

    @Override
    public Page<Workation> search(WorkationSearchParams params, Pageable pageable) {
        Page<WorkationEntity> page =
                repository.search(
                        params.getEmployee(),
                        params.getOriginCountry(),
                        pageable
                );

        return page.map(mapper::toDomain);
    }

    @Override
    public Workation update(Long id, UpdateWorkationRequest body) {
        WorkationEntity entity = repository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new EntityNotFoundException("Workation not found"));

        entity.setDestinationCountry(body.getDestinationCountry());
        entity.setStartDate(body.getStartDate());
        entity.setEndDate(body.getEndDate());

        return mapper.toDomain(entity);
    }

    @Override
    public void delete(Long id) {
        WorkationEntity entity = repository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new EntityNotFoundException("Workation not found"));

        entity.setDeletedAt(LocalDate.now());
    }
}
