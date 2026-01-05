package com.workflex.service;

import com.workflex.api.dto.CreateWorkationRequest;
import com.workflex.api.dto.UpdateWorkationRequest;
import com.workflex.api.dto.WorkationSearchParams;

import com.workflex.domain.Workation;
import com.workflex.mapper.WorkationEntityMapper;
import com.workflex.persistence.WorkationEntity;
import com.workflex.persistence.WorkationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkationServiceImpl implements WorkationService {

    private final WorkationRepository repository;
    private final WorkationEntityMapper mapper;


    @Override
    public Workation create(CreateWorkationRequest command) {
        return null;
    }

    @Override
    public Workation getById(String id) {
        return null;
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
    public Workation update(String id, UpdateWorkationRequest command) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
