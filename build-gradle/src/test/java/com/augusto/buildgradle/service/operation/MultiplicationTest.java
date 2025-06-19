package com.augusto.buildgradle.service.operation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MultiplicationTest {

    @InjectMocks
    private Multiplication multiplication;

    @Test
    void shouldReturnCorrectProduct() {
        assertEquals(50, multiplication.calculate(10, 5).doubleValue(), 0.001);
    }

}