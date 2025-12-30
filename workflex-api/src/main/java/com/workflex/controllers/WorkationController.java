package com.workflex.controllers;

import com.workflex.domain.dtos.GetWorkation;
import com.workflex.domain.dtos.WorkationDto;
import com.workflex.services.WorkationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workflex/workation")
@RequiredArgsConstructor
@Validated
public class WorkationController {
    private final WorkationService workationService;

    @GetMapping
    public List<WorkationDto> getAll(
            GetWorkation params
    ) {
        return workationService.getAllWorkations(params);
    }


    @GetMapping("/{id}")
    public WorkationDto getById(@PathVariable Long id){
        return workationService.getWorkationById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public WorkationDto createWorkation(@Valid @RequestBody WorkationDto body){
        return workationService.createWorkation(body);
    }
}
