package com.augusto.unittesting.config;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.validateMockitoUsage;

@ExtendWith(MockitoExtension.class)
public abstract class UnitTestBase {

    @AfterEach
    void validateMocks() {
        validateMockitoUsage();
    }
}
