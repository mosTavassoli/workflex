package com.workflex.services;

import com.workflex.domain.dtos.CreateWorkationRequest;
import com.workflex.domain.dtos.GetWorkationDto;
import com.workflex.domain.dtos.UpdateWorkationRequest;
import com.workflex.domain.dtos.WorkationDto;
import org.springframework.data.domain.Page;


import org.springframework.data.domain.Pageable;


public interface WorkationService {
    Page<WorkationDto> getAllWorkations(GetWorkationDto params, Pageable pageable);
    WorkationDto getWorkationById(Long id);
    WorkationDto createWorkation(CreateWorkationRequest body);
    WorkationDto updateWorkation(Long id, UpdateWorkationRequest body);
    void deleteWorkation(Long id);
}