package com.augusto.jenkinsbasics.api;

import com.augusto.jenkinsbasics.dto.CalculationRequest;
import com.augusto.jenkinsbasics.dto.CalculationResponse;
import com.augusto.jenkinsbasics.service.CalculationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class CalculationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CalculationService calculationService;

    @InjectMocks
    private CalculationController controller;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldReturnAdditionCalculationResult() throws Exception {
        CalculationRequest request = new CalculationRequest(10, 5, "ADDITION");
        CalculationResponse response = new CalculationResponse(15);

        Mockito.when(calculationService.doCalculation(Mockito.any(CalculationRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(15));

    }

    @Test
    void shouldReturnSubtractionCalculationResult() throws Exception {
        CalculationRequest request = new CalculationRequest(10, 5, "subtraction");
        CalculationResponse response = new CalculationResponse(5);

        Mockito.when(calculationService.doCalculation(Mockito.any(CalculationRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(5));
    }

    @Test
    void shouldReturnDivisionCalculationResult() throws Exception {
        CalculationRequest request = new CalculationRequest(10, 5, "division");
        CalculationResponse response = new CalculationResponse(2);

        Mockito.when(calculationService.doCalculation(Mockito.any(CalculationRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(2));
    }

    @Test
    void shouldReturnMultiplicationCalculationResult() throws Exception {
        CalculationRequest request = new CalculationRequest(10, 5, "multiplication");
        CalculationResponse response = new CalculationResponse(50);

        Mockito.when(calculationService.doCalculation(Mockito.any(CalculationRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(50));
    }

}