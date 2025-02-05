package com.augusto.jenkinsbasics.service.operation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AdditionTest {

    @InjectMocks
    private Addition addition;

    @Test
    void shouldReturnCorrectSum() {
        assertEquals(15, addition.calculate(10, 5).doubleValue(), 0.001);
    }
}