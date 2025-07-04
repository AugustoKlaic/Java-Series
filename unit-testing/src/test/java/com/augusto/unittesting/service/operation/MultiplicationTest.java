package com.augusto.unittesting.service.operation;

import com.augusto.unittesting.commons.UnitTestBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MultiplicationTest extends UnitTestBase {

    @InjectMocks
    private Multiplication multiplication;

    @Test
    void shouldReturnCorrectProduct() {
        assertEquals(50, multiplication.calculate(10, 5).doubleValue(), 0.001);
    }

}