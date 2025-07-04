package com.augusto.unittesting.service;

import com.augusto.unittesting.commons.UnitTestBase;
import com.augusto.unittesting.service.operation.Addition;
import com.augusto.unittesting.service.operation.Division;
import com.augusto.unittesting.service.operation.Multiplication;
import com.augusto.unittesting.service.operation.Subtraction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OperationEnumTest extends UnitTestBase {

    @Test
    void shouldReturnCorrectOperation() {
        assertInstanceOf(Addition.class, OperationEnum.getOperation("ADDITION"));
        assertInstanceOf(Subtraction.class, OperationEnum.getOperation("SUBTRACTION"));
        assertInstanceOf(Multiplication.class, OperationEnum.getOperation("MULTIPLICATION"));
        assertInstanceOf(Division.class, OperationEnum.getOperation("DIVISION"));
    }

    @Test
    void shouldThrowExceptionForInvalidOperation() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> OperationEnum.getOperation("INVALID_OPERATION"));

        assertEquals("Invalid Operation: INVALID_OPERATION", exception.getMessage());
    }

}