package com.augusto.unittesting.service.operation;

public class Addition implements MathOperation {
    @Override
    public Number calculate(Number operator, Number operand) {
        return operator.doubleValue() + operand.doubleValue();
    }
}
