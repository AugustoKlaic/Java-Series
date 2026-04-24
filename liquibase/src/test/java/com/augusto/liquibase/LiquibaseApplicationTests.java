package com.augusto.liquibase;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled("Requires a running PostgreSQL instance")
class LiquibaseApplicationTests {

    @Test
    void contextLoads() {
    }

}
