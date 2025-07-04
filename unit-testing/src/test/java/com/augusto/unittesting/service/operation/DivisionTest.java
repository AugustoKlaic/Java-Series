package com.augusto.unittesting.service.operation;

import com.augusto.unittesting.commons.UnitTestBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class DivisionTest extends UnitTestBase {

    @InjectMocks
    private Division division;

    @Test
    void shouldReturnCorrectQuotient() {
        assertEquals(2, division.calculate(10, 5).doubleValue(), 0.001);
    }

    @Test
    void shouldThrowExceptionForDivisionByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> division.calculate(10, 0));
        assertEquals("zero division not permitted", exception.getMessage());
    }

}