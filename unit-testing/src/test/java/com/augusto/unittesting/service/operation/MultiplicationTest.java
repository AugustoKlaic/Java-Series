package com.augusto.unittesting.service.operation;

import com.augusto.unittesting.commons.UnitTestBase;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiplicationTest extends UnitTestBase {

    @InjectMocks
    private Multiplication multiplication;

    @Test
    void shouldReturnCorrectProduct() {
        assertEquals(50, multiplication.calculate(10, 5).doubleValue(), 0.001);
    }

}