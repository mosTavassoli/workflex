package com.workflex.controllers;

import com.workflex.domain.dtos.WorkationDto;
import com.workflex.services.WorkationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workflex/workation")
@RequiredArgsConstructor
public class WorkationController {
    private final WorkationService workationService;

    @GetMapping
    public List<WorkationDto> getAll() {
        return workationService.getAllWorkations();
    }
}
