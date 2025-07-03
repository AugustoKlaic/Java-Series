package com.augusto.unittesting.service.operation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SubtractionTest {

    @InjectMocks
    private Subtraction subtraction;

    @Test
    void shouldReturnCorrectDifference() {
        assertEquals(5, subtraction.calculate(10, 5).doubleValue(), 0.001);
    }

}