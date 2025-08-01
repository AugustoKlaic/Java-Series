package com.augusto.unittesting.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {

    public void sendWelcomeMessage(String name) {
        log.info(getWelcomePrefix() + " Message sent to: {}", name);
    }

    public String getWelcomePrefix() {
        log.info("executou getWelcomePrefix");
        return "Welcome!";
    }
}
