package com.workflex.service;

import com.workflex.persistence.WorkationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CsvImportServiceTest {

    @Mock
    WorkationRepository workationRepository;

    @InjectMocks
    CsvImportService service;

    @Test
    void importIfEmpty_shouldSkipWhenDataExists() {
        when(workationRepository.count()).thenReturn(10L);

        service.importIfEmpty();

        verify(workationRepository, never()).saveAll(any());
    }
}