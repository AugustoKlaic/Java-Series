package com.augusto.elkbasics.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryToLog {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryToLog.class);

    public void logRepository() {
        logger.info("Log from repository: {}", this);
    }
}
