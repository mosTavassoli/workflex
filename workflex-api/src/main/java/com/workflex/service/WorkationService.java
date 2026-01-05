package com.workflex.service;

import com.workflex.api.dto.CreateWorkationRequest;
import com.workflex.api.dto.UpdateWorkationRequest;
import com.workflex.api.dto.WorkationSearchParams;
import com.workflex.domain.Workation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WorkationService {

    Workation create(CreateWorkationRequest command);

    Workation getById(String id);

    Page<Workation> search(WorkationSearchParams params, Pageable pageable);

    Workation update(String id, UpdateWorkationRequest command);

    void delete(String id);
}
