package com.workflex.Integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workflex.domain.dtos.UpdateWorkationRequest;
import com.workflex.domain.models.Workation;
import com.workflex.repositories.WorkationRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static com.workflex.support.WorkationTestSupport.BASE_URL;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class WorkationUpdateIntegrationTest {

    @Autowired MockMvc mockMvc;
    @Autowired WorkationRepository workationRepository;
    @Autowired
    ObjectMapper objectMapper;

    private Long existingId;

    @BeforeEach
    void setup() {
        Workation saved = workationRepository.save(
                Workation.builder()
                        .workationId("W-100")
                        .employee("Alice")
                        .originCountry("Italy")
                        .destinationCountry("Spain")
                        .startDate(LocalDate.of(2025, 1, 10))
                        .endDate(LocalDate.of(2025, 1, 20))
                        .build()
        );
        existingId = saved.getId();
    }

    @Test
    void shouldUpdateWorkationAndReturn200() throws Exception {
        UpdateWorkationRequest request = new UpdateWorkationRequest(
                "W-100",
                "Alice 1",
                "Germany",
                "Spain"
        );

        mockMvc.perform(put(BASE_URL + "/" + existingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employee").value("Alice 1"))
                .andExpect(jsonPath("$.originCountry").value("Germany"));
    }
}
