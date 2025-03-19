package com.augusto.elkbasics.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class ControllerToLog {

    private static final Logger logger = LoggerFactory.getLogger(ControllerToLog.class);

    private final ServiceToLog service;

    public ControllerToLog(ServiceToLog service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Void> startLogging() {
        logger.info("Log from: {}", this);
        service.logSomething();
        return ResponseEntity.noContent().build();
    }
}
