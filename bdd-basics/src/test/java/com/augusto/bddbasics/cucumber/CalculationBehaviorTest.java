package com.augusto.bddbasics.cucumber;

import com.augusto.bddbasics.dto.CalculationRequest;
import com.augusto.bddbasics.dto.CalculationResponse;
import com.augusto.bddbasics.service.CalculationService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CalculationBehaviorTest {

    private CalculationRequest request;
    private CalculationResponse result;
    private Exception exception;

    @Autowired
    private CalculationService service;

    @Given("operator is {double} and operand is {double} and operation is {string}")
    public void givenOperatorAndOperand(Number operator, Number operand, String operation) {
        request = new CalculationRequest(operator, operand, operation);
        exception = null;
    }

    @When("calculation is performed")
    public void whenOperatorIsAddedWithOperand() {
        try {
            result = service.doCalculation(request);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("result must be {double}")
    public void thenResultMustBe(Number result) {
        assertEquals(result, this.result.getResult());
    }

    @Then("an exception is thrown with message {string}")
    public void thenExceptionIsThrown(String expectedMessage) {
        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
    }
}
