package com.workflex.Integrations;

import com.workflex.domain.models.Workation;
import com.workflex.repositories.WorkationRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static com.workflex.support.WorkationTestSupport.BASE_URL;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class WorkationQueryIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    WorkationRepository workationRepository;

    @BeforeEach
    void seedData() {
        workationRepository.save(
                Workation.builder()
                        .workationId("W-401")
                        .employee("john")
                        .originCountry("Italy")
                        .destinationCountry("Spain")
                        .startDate(LocalDate.of(2025, 1, 10))
                        .endDate(LocalDate.of(2025, 1, 20))
                        .build()
        );

        workationRepository.save(
                Workation.builder()
                        .workationId("W-402")
                        .employee("alice")
                        .originCountry("Italy")
                        .destinationCountry("France")
                        .startDate(LocalDate.of(2025, 1, 10))
                        .endDate(LocalDate.of(2025, 1, 20))
                        .build()
        );
    }

    @Test
    void shouldFilterByEmployee_caseInsensitive_andReturnPagedResult() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("employee", "JOHN")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "workationId,asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].employee").value("john"))
                .andExpect(jsonPath("$.content[0].workationId").value("W-401"))
                .andExpect(jsonPath("$.totalElements").value(1));
    }

    @Test
    void shouldReturnEmptyPageWhenNoMatch() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("employee", "nobody")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(0))
                .andExpect(jsonPath("$.totalElements").value(0));
    }
}