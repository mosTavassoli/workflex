package com.workflex.controllers;

import com.workflex.domain.dtos.GetWorkation;
import com.workflex.domain.dtos.WorkationDto;
import com.workflex.domain.enums.RiskLevel;
import com.workflex.services.WorkationService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(WorkationController.class)
class WorkationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WorkationService workationService;


    List<WorkationDto> workations;

    @BeforeEach
    void setUp() {
        WorkationDto workation1 = createWorkationDto(
                "1",
                "John Doe",
                "Germany",
                "Spain",
                LocalDate.of(2024, 1, 15),
                LocalDate.of(2024, 1, 30),
                10,
                RiskLevel.NO
        );

        WorkationDto workation2 = createWorkationDto(
                "2",
                "Jane Smith",
                "United States",
                "India",
                LocalDate.of(2024, 2, 1),
                LocalDate.of(2024, 3, 1),
                20,
                RiskLevel.HIGH
        );

        workations = Arrays.asList(workation1, workation2);
    }


    @Test
    void getAll_shouldReturnListOfWorkations_whenWorkationsExist() throws Exception {
        // Arrange



        when(workationService.getAllWorkations(any(GetWorkation.class))).thenReturn(workations);

        // Act & Assert
        mockMvc.perform(get("/workflex/workation")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].workationId", is("1")))
                .andExpect(jsonPath("$[0].employee", is("John Doe")))
                .andExpect(jsonPath("$[0].originCountry", is("Germany")))
                .andExpect(jsonPath("$[0].destinationCountry", is("Spain")))
                .andExpect(jsonPath("$[0].startDate", is("15/01/2024")))
                .andExpect(jsonPath("$[0].endDate", is("30/01/2024")))
                .andExpect(jsonPath("$[0].workingDays", is(10)))
                .andExpect(jsonPath("$[0].riskLevel", is("NO")))
                .andExpect(jsonPath("$[1].workationId", is("2")))
                .andExpect(jsonPath("$[1].employee", is("Jane Smith")))
                .andExpect(jsonPath("$[1].riskLevel", is("HIGH")));
    }

    @Test
    void getAll_shouldReturnEmptyList_whenNoWorkationsExist() throws Exception {
        // Arrange
        when(workationService.getAllWorkations(any(GetWorkation.class))).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/workflex/workation")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(jsonPath("$", is(empty())));
    }

    @Test
    void getAll_shouldReturnCorrectDateFormat() throws Exception {
        // Arrange
        WorkationDto workation = createWorkationDto(
                "1",
                "Test Employee",
                "Germany",
                "Spain",
                LocalDate.of(2024, 12, 25),
                LocalDate.of(2025, 1, 5),
                5,
                RiskLevel.LOW
        );

        when(workationService.getAllWorkations(any(GetWorkation.class))).thenReturn(List.of(workation));

        // Act & Assert
        mockMvc.perform(get("/workflex/workation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].startDate", is("25/12/2024")))
                .andExpect(jsonPath("$[0].endDate", is("05/01/2025")));
    }

    @Test
    void getAll_shouldHandleAllRiskLevels() throws Exception {
        // Arrange
        WorkationDto highRisk = createWorkationDto("1", "Employee 1", "US", "India",
                LocalDate.now(), LocalDate.now().plusDays(10), 8, RiskLevel.HIGH);
        WorkationDto lowRisk = createWorkationDto("2", "Employee 2", "Germany", "Spain",
                LocalDate.now(), LocalDate.now().plusDays(10), 8, RiskLevel.LOW);
        WorkationDto noRisk = createWorkationDto("3", "Employee 3", "Belgium", "Greece",
                LocalDate.now(), LocalDate.now().plusDays(10), 8, RiskLevel.NO);

        when(workationService.getAllWorkations(any(GetWorkation.class))).thenReturn(Arrays.asList(highRisk, lowRisk, noRisk));

        // Act & Assert
        mockMvc.perform(get("/workflex/workation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].riskLevel", is("HIGH")))
                .andExpect(jsonPath("$[1].riskLevel", is("LOW")))
                .andExpect(jsonPath("$[2].riskLevel", is("NO")));
    }

    @Test
    void getAll_shouldUseCorrectEndpoint() throws Exception {
        // Arrange
        when(workationService.getAllWorkations(any(GetWorkation.class))).thenReturn(Collections.emptyList());

        // Act & Assert - verify correct endpoint
        mockMvc.perform(get("/workflex/workation"))
                .andExpect(status().isOk());

        // Verify incorrect endpoints return 404
        mockMvc.perform(get("/workflex/workations"))
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/workation"))
                .andExpect(status().isNotFound());
    }

    // Helper method to create WorkationDto for testing
    private WorkationDto createWorkationDto(String id, String employee, String origin,
                                            String destination, LocalDate startDate,
                                            LocalDate endDate, Integer workingDays,
                                            RiskLevel riskLevel) {
        return WorkationDto.builder().workationId(id)
                .employee(employee)
                .originCountry(origin)
                .destinationCountry(destination)
                .startDate(startDate)
                .endDate(endDate)
                .workingDays(workingDays)
                .riskLevel(riskLevel).build();
    }


    @Test
    void returnCorrectWorkstationById() throws Exception {
        when(workationService.getWorkationById(Mockito.anyLong()))
                .thenReturn(workations.get(0));

        mockMvc.perform(get("/workflex/workation/1")).
                andExpect(status().isOk());

    }

    @Test
    void notFoundWorkationIfPassWrongId() throws Exception {
        when(workationService.getWorkationById(100L)).thenThrow(
                new EntityNotFoundException("Workation not found"));

        mockMvc.perform(get("/workflex/workation/100")).andExpect(status().isNotFound());
    }


    @Test
    void shouldCreateWorkationSuccessfully() throws Exception {
        WorkationDto newWorkationDto =
                WorkationDto.builder().id(10L).workationId("10").build();

        when(workationService.createWorkation(any(WorkationDto.class)))
                .thenReturn(newWorkationDto);

        String json = """
                  {
                    "id":10,
                    "workationId":"10"
                  }
                """;

        mockMvc.perform(post("/workflex/workation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isCreated());

    }

    @Test
    void  shouldNotCreateAndThrowExceptionIfWorkationIdIsNull() throws Exception {
        String json = """
                  {
                    "id":10,
                    "workationId":""
                  }
                """;

        verify(workationService, never()).createWorkation(any());

        mockMvc.perform(post("/workflex/workation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isBadRequest());

    }

    @Test
    void shouldReturnWorkationsFilteredByEmployee() throws Exception {

        when(workationService.getAllWorkations(any(GetWorkation.class)))
                .thenReturn(workations);

        mockMvc.perform(get("/workflex/workation")
                        .param("employee", "John Doe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].workationId").value("1"))
                .andExpect(jsonPath("$[0].employee").value("John Doe"));
    }

}