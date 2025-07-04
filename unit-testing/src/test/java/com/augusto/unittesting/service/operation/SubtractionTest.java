package com.augusto.unittesting.service.operation;

import com.augusto.unittesting.commons.UnitTestBase;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubtractionTest extends UnitTestBase {

    @InjectMocks
    private Subtraction subtraction;

    @Test
    void shouldReturnCorrectDifference() {
        assertEquals(5, subtraction.calculate(10, 5).doubleValue(), 0.001);
    }

}