package com.workflex.Integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workflex.domain.dtos.CreateWorkationRequest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static com.workflex.support.WorkationTestSupport.BASE_URL;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class WorkationCreateIntegrationTest {

    @Autowired MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldCreateWorkationAndReturn201() throws Exception {
        CreateWorkationRequest request = new CreateWorkationRequest(
                "W-100",
                "Alice",
                "Italy",
                "Spain",
                LocalDate.of(2025, 1, 10),
                LocalDate.of(2025, 1, 20)
        );

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.workationId").value("W-100"))
                .andExpect(jsonPath("$.employee").value("Alice"));
    }
}