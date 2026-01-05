package com.workflex.controllers;

import com.workflex.domain.dtos.CreateWorkationRequest;
import com.workflex.domain.dtos.GetWorkationDto;
import com.workflex.domain.dtos.UpdateWorkationRequest;
import com.workflex.domain.dtos.WorkationDto;
import com.workflex.services.WorkationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/workflex/workation")
@RequiredArgsConstructor
public class WorkationController {
    private final WorkationService workationService;

    @GetMapping
    public ResponseEntity<Page<WorkationDto>> getAll(
            @Valid GetWorkationDto params,
            Pageable pageable
    ) {
        Page<WorkationDto> page =
                workationService.getAllWorkations(params, pageable);
        return ResponseEntity.ok(page);
    }


    @GetMapping("/{id}")
    public WorkationDto getById(@PathVariable Long id) {
        return workationService.getWorkationById(id);
    }


    @PostMapping
    public ResponseEntity<WorkationDto> createWorkation(
            @Valid @RequestBody CreateWorkationRequest body
    ) {
        WorkationDto created =
                workationService.createWorkation(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @PutMapping("/{id}")
    public ResponseEntity<WorkationDto> updateWorkation(
            @PathVariable Long id,
            @Valid @RequestBody UpdateWorkationRequest body) {
        WorkationDto updated =
                workationService.updateWorkation(id, body);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkation(@PathVariable Long id) {
        workationService.deleteWorkation(id);
        return ResponseEntity.noContent().build();
    }
}
