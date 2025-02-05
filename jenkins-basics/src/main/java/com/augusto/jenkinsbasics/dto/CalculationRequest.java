package com.augusto.jenkinsbasics.dto;

public class CalculationRequest {
    private Number operator;
    private Number operand;
    private String mathOperation;

    public CalculationRequest() {
    }

    public CalculationRequest(Number operator, Number operand, String mathOperation) {
        this();
        this.operator = operator;
        this.operand = operand;
        this.mathOperation = mathOperation;
    }

    public Number getOperator() {
        return operator;
    }

    public void setOperator(Number operator) {
        this.operator = operator;
    }

    public Number getOperand() {
        return operand;
    }

    public void setOperand(Number operand) {
        this.operand = operand;
    }

    public String getMathOperation() {
        return mathOperation;
    }

    public void setMathOperation(String mathOperation) {
        this.mathOperation = mathOperation;
    }
}
