package com.augusto.bddbasics.dto;

public class CalculationResponse {
    private Number result;

    public CalculationResponse(Number result) {
        this.result = result;
    }

    public Number getResult() {
        return result;
    }
}
