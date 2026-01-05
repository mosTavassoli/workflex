package com.workflex.api;

import com.workflex.api.dto.PagedResponse;
import com.workflex.api.dto.WorkationResponse;
import com.workflex.api.dto.WorkationSearchParams;
import com.workflex.domain.Workation;

import com.workflex.mapper.WorkationApiMapper;
import com.workflex.service.WorkationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/workations")
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

}
