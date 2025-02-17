package com.augusto.bddbasics.cucumber;


import com.augusto.bddbasics.dto.CalculationRequest;
import com.augusto.bddbasics.dto.CalculationResponse;
import com.augusto.bddbasics.service.CalculationService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubtractionBehaviorTest {


    private CalculationRequest request;
    private CalculationResponse result;

    @Autowired
    private CalculationService service;

    @Given("operator is {double} and operand is {double} and operation is {string}")
    public void givenOperatorAndOperand(Number operator, Number operand, String operation) {
        request = new CalculationRequest(operator, operand, operation);
    }

    @When("operator is subtracted with operand")
    public void whenOperatorIsSubtractedWithOperand() {
        result = service.doCalculation(request);
    }

    @Then("result must be {double}")
    public void thenResultMustBe(Number result) {
        assertEquals(result, this.result.getResult());
    }
}
