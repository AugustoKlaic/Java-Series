package com.augusto.elkbasics.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class ServiceToLog {
    private static final Logger logger = LoggerFactory.getLogger(ServiceToLog.class);

    private final RepositoryToLog repository;

    public ServiceToLog(RepositoryToLog repository) {
        this.repository = repository;
    }

    public void logSomething() {
        for (int i = 0; i < 10; i++) {
            logger.info("Log number: {} from service {}", i, this);
        }

        repository.logRepository();

    }

}
