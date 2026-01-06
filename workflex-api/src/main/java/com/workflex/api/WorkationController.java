package com.workflex.api;

import com.workflex.api.dto.*;
import com.workflex.domain.Workation;

import com.workflex.mapper.WorkationApiMapper;
import com.workflex.service.WorkationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/workations")
@RequiredArgsConstructor
public class WorkationController {

    private final WorkationService service;
    private final WorkationApiMapper mapper;

    @GetMapping
    public PagedResponse<WorkationResponse> search(
            @ModelAttribute WorkationSearchParams params,
            Pageable pageable
    ) {
        Page<Workation> page = service.search(params, pageable);

        return PagedResponse.from(
                page,
                mapper.toResponses(page.getContent())
        );
    }

    @GetMapping("/{id}")
    public WorkationResponse getById(@PathVariable Long id) {
        Workation response = service.getById(id);

        return mapper.toResponse(response);
    }

    @PostMapping
    public WorkationResponse create(
            @Valid @RequestBody CreateWorkationRequest body
    ) {
        Workation response = service.create(body);

        return mapper.toResponse(response);
    }

    @PatchMapping("/{id}")
    public WorkationResponse update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateWorkationRequest body
    ) {
        Workation response = service.update(id, body);

        return mapper.toResponse(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
