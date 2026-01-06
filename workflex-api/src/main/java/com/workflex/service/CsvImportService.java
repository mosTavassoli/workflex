package com.workflex.service;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;
import com.workflex.domain.enums.RiskLevel;

import com.workflex.persistence.WorkationEntity;
import com.workflex.persistence.WorkationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.workflex.exceptions.CsvImportFailedException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CsvImportService {
    private final WorkationRepository workationRepository;

    public void importIfEmpty() {
        if (workationRepository.count() > 0) {
            log.info("Skipping CSV import: workations table already contains data (count={}).", workationRepository.count());
            return;
        }

        try (var is = new ClassPathResource("workations.csv").getInputStream();
             var reader = new CSVReaderHeaderAware(new InputStreamReader(is))) {

            List<WorkationEntity> buffer = new ArrayList<>();
            Map<String, String> row;

            while ((row = reader.readMap()) != null) {
                WorkationEntity w = mapRow(row);
                buffer.add(w);
            }

            workationRepository.saveAll(buffer);
            log.info("Imported {} workations from CSV.", buffer.size());


        } catch (IOException | CsvValidationException e) {
            throw new CsvImportFailedException("Failed to import workations.csv", e);
        }
    }

    private WorkationEntity mapRow(Map<String, String> row) {
        WorkationEntity w = new WorkationEntity();

        w.setWorkationId(row.get("workationId"));
        w.setEmployee(row.get("employee"));
        w.setOriginCountry(row.get("origin"));
        w.setDestinationCountry(row.get("destination"));
        w.setStartDate(LocalDate.parse(row.get("start")));
        w.setEndDate(LocalDate.parse(row.get("end")));
        w.setWorkingDays(Integer.parseInt(row.get("workingDays")));
        w.setRiskLevel(RiskLevel.valueOf(row.get("risk").toUpperCase()));

        return w;
    }
};
