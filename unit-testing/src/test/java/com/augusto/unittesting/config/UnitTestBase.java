package com.augusto.unittesting.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

import static org.mockito.Mockito.inOrder;

@Slf4j
@ExtendWith(MockitoExtension.class)
public abstract class UnitTestBase {

    private Clock clock;
    private Instant start;
    protected InOrder inOrder;

    @BeforeEach
    void setup(TestInfo testInfo) {
        this.clock = Clock.systemDefaultZone();
        this.start = Instant.now(clock);
        log.info("Starting test: {}", testInfo.getDisplayName());
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        Instant end = Instant.now(clock);
        Duration duration = Duration.between(start, end);
        log.info("Finished test: {} in {} ms", testInfo.getDisplayName(), duration.toMillis());
    }

    @SafeVarargs
    protected final <T> void inOrderOf(T... mocks) {
        this.inOrder = inOrder((Object[]) mocks);
    }
}
