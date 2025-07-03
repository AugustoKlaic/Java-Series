package com.augusto.unittesting.commons;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.validateMockitoUsage;

@ExtendWith(MockitoExtension.class)
public abstract class UnitTestBase {

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void validateMocks() {
        validateMockitoUsage();
    }
}
