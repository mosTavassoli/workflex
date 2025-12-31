package com.workflex.controllers;

import com.workflex.domain.dtos.GetWorkation;
import com.workflex.domain.dtos.WorkationDto;
import com.workflex.services.WorkationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public WorkationDto getById(@PathVariable Long id) {
        return workationService.getWorkationById(id);
    }

    @PostMapping("")
    public ResponseEntity<WorkationDto> createWorkation(@Valid @RequestBody WorkationDto body) {
        WorkationDto created = workationService.createWorkation(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkationDto> updateWorkation(
            @PathVariable Long id,
            @Valid @RequestBody WorkationDto body) {
        WorkationDto updated = workationService.updateWorkation(id, body);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkation(@PathVariable Long id){
        workationService.deleteWorkation(id);
        return ResponseEntity.noContent().build();
    }
}
