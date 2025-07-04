package com.augusto.unittesting.service;


import com.augusto.unittesting.commons.UnitTestBase;
import com.augusto.unittesting.dto.CalculationRequest;
import com.augusto.unittesting.dto.CalculationResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CalculationServiceTest extends UnitTestBase {

    @InjectMocks
    private CalculationService service;

    @Test
    void shouldPerformAdditionCorrectly() {
        CalculationRequest request = new CalculationRequest(10, 5, "ADDITION");
        CalculationResponse response = service.doCalculation(request);
        assertEquals(15.0, response.getResult());
    }

    @Test
    void shouldPerformSubtractionCorrectly() {
        CalculationRequest request = new CalculationRequest(10, 5, "SUBTRACTION");
        CalculationResponse response = service.doCalculation(request);
        assertEquals(5.0, response.getResult());
    }

    @Test
    void shouldPerformMultiplicationCorrectly() {
        CalculationRequest request = new CalculationRequest(10, 5, "MULTIPLICATION");
        CalculationResponse response = service.doCalculation(request);
        assertEquals(50.0, response.getResult());
    }

    @Test
    void shouldPerformDivisionCorrectly() {
        CalculationRequest request = new CalculationRequest(10, 5, "DIVISION");
        CalculationResponse response = service.doCalculation(request);
        assertEquals(2.0, response.getResult());
    }

    @Test
    void shouldThrowExceptionForInvalidOperation() {
        CalculationRequest request = new CalculationRequest(10, 5, "INVALID_OP");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.doCalculation(request));
        assertEquals("Invalid Operation: INVALID_OP", exception.getMessage());
    }
}