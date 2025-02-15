package com.augusto.bddbasics.cucumber;

import com.augusto.bddbasics.dto.CalculationRequest;
import com.augusto.bddbasics.dto.CalculationResponse;
import com.augusto.bddbasics.service.CalculationService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@CucumberContextConfiguration
public class AdditionBehaviorTest {

    private CalculationRequest request;
    private CalculationResponse result;

    @Autowired
    private CalculationService service;

    @Given("operator is {double} and operand is {double} and operation is {string}")
    public void givenOperatorAndOperand(Number operator, Number operand, String operation) {
        request = new CalculationRequest(operator, operand, operation);
    }

    @When("operator is added with operand")
    public void whenOperatorIsAddedWithOperand() {
        result = service.doCalculation(request);
    }

    @Then("result must be {double}")
    public void thenResultMustBe(Number result) {
        assertEquals(result, this.result.getResult());
    }
}
