package com.workflex.Integrations;


import com.workflex.persistence.WorkationEntity;
import com.workflex.persistence.WorkationRepository;
import com.workflex.service.CsvImportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.util.List;

@SpringBootTest
public class CsvImportServiceIntegrationTest {
    @Autowired
    private CsvImportService service;

    @Autowired
    private WorkationRepository repository;

    @Test
    void shouldImportCsvWhenRepositoryIsEmpty() {
        repository.deleteAll();

        service.importIfEmpty();

        List<WorkationEntity> all = repository.findAll();
        assertThat(all).hasSize(5);
        assertThat(all.get(0).getEmployee()).isEqualTo("Steffen Jacobs");
    }
}
