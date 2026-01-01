package com.workflex.Integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class WorkationIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String BASE_URL = "/workflex/workation";


    @BeforeEach
    void setUp() throws Exception {
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
            {
              "workationId": "W-401",
              "employee": "john",
              "originCountry": "Italy",
              "destinationCountry": "Spain",
              "startDate": "10/01/2025",
              "endDate": "20/01/2025"
            }
            """))
                .andExpect(status().isCreated());

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
            {
              "workationId": "W-402",
              "employee": "alice",
              "originCountry": "Italy",
              "destinationCountry": "France",
              "startDate": "10/01/2025",
              "endDate": "20/01/2025"
            }
            """))
                .andExpect(status().isCreated());
    }



    @Test
    void shouldCreateWorkationAndReturn201() throws Exception {
        String json = """
                {
                  "workationId": "W-100",
                  "employee": "Alice",
                  "originCountry": "Italy",
                  "destinationCountry": "Spain",
                  "startDate": "10/01/2025",
                  "endDate": "20/01/2025"
                }
                """;

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.workationId").value("W-100"))
                .andExpect(jsonPath("$.employee").value("Alice"));
    }


    @Test
    void shouldBindEmployeeParamIntoGetWorkationAndFilterResults() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("employee", "john")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].employee").value("john"))
                .andExpect(jsonPath("$.content[0].workationId").value("W-401"));
    }

}
