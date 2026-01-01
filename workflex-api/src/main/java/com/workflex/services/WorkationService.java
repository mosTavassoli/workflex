package com.workflex.services;

import com.workflex.domain.dtos.GetWorkation;
import com.workflex.domain.dtos.WorkationDto;
import org.springframework.data.domain.Page;


import org.springframework.data.domain.Pageable;


public interface WorkationService {
    Page<WorkationDto> getAllWorkations(GetWorkation params, Pageable pageable);
    WorkationDto getWorkationById(Long id);
    WorkationDto createWorkation(WorkationDto body);
    WorkationDto updateWorkation(Long id, WorkationDto body);
    void deleteWorkation(Long id);
}