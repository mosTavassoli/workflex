package com.workflex.service;

import com.workflex.api.dto.CreateWorkationRequest;
import com.workflex.api.dto.UpdateWorkationRequest;
import com.workflex.api.dto.WorkationSearchParams;
import com.workflex.domain.Workation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WorkationService {

    Workation create(CreateWorkationRequest body);

    Workation getById(Long id);

    Page<Workation> search(WorkationSearchParams params, Pageable pageable);

    Workation update(Long id, UpdateWorkationRequest body);

    void delete(Long id);
}
