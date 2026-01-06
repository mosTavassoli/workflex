package com.workflex.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.workflex.api.dto.CreateWorkationRequest;
import com.workflex.api.dto.UpdateWorkationRequest;
import com.workflex.api.dto.WorkationResponse;
import com.workflex.domain.Workation;
import com.workflex.mapper.WorkationApiMapper;
import com.workflex.service.WorkationService;

import static com.workflex.support.WorkationTestSupport.BASE_URL;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WorkationController.class)
public class WorkationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WorkationService service;

    @MockitoBean
    private WorkationApiMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void search_shouldReturnPagedWorkations() throws Exception {
        Workation domain =
                Workation.create("john",
                        "IYALY",
                        "Germany",
                        LocalDate.now(),
                        LocalDate.now()
                );

        WorkationResponse response =
                WorkationResponse.builder().id("10L")
                        .employee("john")
                        .originCountry("ITALY")
                        .build();

        Page<Workation> page = new PageImpl<>(
                List.of(domain),
                PageRequest.of(0, 10), 1);

        when(service.search(any(), any())).thenReturn(page);
        when(mapper.toResponses(any())).thenReturn(List.of(response));

        mockMvc.perform(get(BASE_URL)
                        .param("employee", "john")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].employee").value("john"))
                .andExpect(jsonPath("$.page.totalElements").value(1));
    }

    @Test
    void create_shouldCreateWorkation() throws Exception {
        Workation domain = Workation.create(
                "john",
                "ITALY",
                "GERMANY",
                LocalDate.now(),
                LocalDate.of(2027, 10, 2)
        );

        WorkationResponse response =
                WorkationResponse.builder()
                        .id("10L")
                        .employee("john")
                        .originCountry("ITALY")
                        .startDate(LocalDate.now())
                        .endDate(LocalDate.of(2027, 10, 2))
                        .build();


        when(service.create(any())).thenReturn(domain);
        when(mapper.toResponse(any())).thenReturn(response);

        CreateWorkationRequest requestBody =
                CreateWorkationRequest.builder()
                        .employee("john")
                        .originCountry("ITALY")
                        .destinationCountry("GERMANY")
                        .startDate(LocalDate.now())
                        .endDate(LocalDate.of(2027, 10, 2))
                        .build();

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.employee").value("john"));
    }

    @Test
    void update_shouldUpdateWorkationById() throws Exception {
        Workation domain = Workation.create(
                "Alice",
                "ITALY",
                "GERMANY",
                LocalDate.now(),
                LocalDate.of(2027, 10, 2)
        );

        WorkationResponse response =
                WorkationResponse.builder()
                        .id("10L")
                        .employee("john")
                        .originCountry("ITALY")
                        .destinationCountry("France")
                        .startDate(LocalDate.now())
                        .endDate(LocalDate.of(2027, 10, 2))
                        .build();


        when(service.update(eq(10L), any())).thenReturn(domain);
        when(mapper.toResponse(any())).thenReturn(response);

        UpdateWorkationRequest requestBody =
                UpdateWorkationRequest.builder()
                        .destinationCountry("France")
                        .startDate(LocalDate.now())
                        .endDate(LocalDate.of(2027, 10, 2))
                        .build();

        mockMvc.perform(patch(BASE_URL + "/{id}", 10L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employee").value("john"))
                .andExpect(jsonPath("$.destinationCountry").value("France"));
    }
}
