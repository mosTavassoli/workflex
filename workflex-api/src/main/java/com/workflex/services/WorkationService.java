package com.workflex.services;

import com.workflex.domain.dtos.WorkationDto;
import com.workflex.domain.mappers.WorkationMapper;
import com.workflex.repositories.WorkationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkationService {
    private final WorkationRepository repository;
    private final WorkationMapper mapper;

    public List<WorkationDto> getAllWorkations() {
        return mapper.toDtoList(repository.findAll());
    }
}