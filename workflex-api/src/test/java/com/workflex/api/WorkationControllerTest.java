package com.workflex.api;


import com.workflex.api.dto.WorkationResponse;
import com.workflex.domain.Workation;
import com.workflex.mapper.WorkationApiMapper;
import com.workflex.service.WorkationService;

import static com.workflex.utils.WorkationTestSupport.BASE_URL;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


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


    @Test
    void search_shouldReturnPagedWorkations() throws Exception {
        Workation domain =
                Workation.builder()
                        .id("10L")
                        .employee("john")
                        .originCountry("ITALY")
                        .build();

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
}
