package com.workflex.services;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;
import com.workflex.domain.enums.RiskLevel;
import com.workflex.domain.models.Workation;
import com.workflex.repositories.WorkationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

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

    public void importIfEmpty(){
        if(workationRepository.count() > 0){
            log.info("Skipping CSV import: workations table already contains data (count={}).", workationRepository.count());
            return;
        }

        try (var is = new ClassPathResource("workations.csv").getInputStream();
             var reader = new CSVReaderHeaderAware(new InputStreamReader(is))) {

            List<Workation> buffer = new ArrayList<>();
            Map<String,String> row;

            while ((row = reader.readMap()) != null) {
                Workation w = mapRow(row);
                buffer.add(w);
            }

            workationRepository.saveAll(buffer);
            log.info("Imported {} workations from CSV.", buffer.size());


        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("Failed to import workations.csv", e);
        }
    }

    private Workation mapRow(Map<String, String> row) {
        // CSV headers exactly as provided:
        // workationId,employee,origin,destination,start,end,workingDays,risk
        String id = row.get("workationId");
        String employee = row.get("employee");
        String origin = row.get("origin");
        String destination = row.get("destination");
        LocalDate startDate = LocalDate.parse(row.get("start")); // yyyy-MM-dd
        LocalDate endDate   = LocalDate.parse(row.get("end"));   // yyyy-MM-dd
        Integer workingDays = Integer.parseInt(row.get("workingDays"));

        // risk: HIGH | LOW | NO
        RiskLevel riskLevel = RiskLevel.valueOf(row.get("risk").toUpperCase());

        return Workation.builder()
                .id(id)
                .employee(employee)
                .originCountry(origin)
                .destinationCountry(destination)
                .startDate(startDate)
                .endDate(endDate)
                .workingDays(workingDays)
                .riskLevel(riskLevel)
                .build();
    }
}
