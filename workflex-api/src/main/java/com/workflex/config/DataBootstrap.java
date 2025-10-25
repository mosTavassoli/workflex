package com.workflex.config;


import com.workflex.services.CsvImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataBootstrap implements CommandLineRunner {
    private final CsvImportService csvImportService;

    @Override
    public void run(String... args) {
        csvImportService.importIfEmpty();
    }
}