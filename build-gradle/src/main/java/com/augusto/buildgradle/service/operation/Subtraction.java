package com.augusto.buildgradle.service.operation;

public class Subtraction implements MathOperation {
    @Override
    public Number calculate(Number operator, Number operand) {
        return operator.doubleValue() - operand.doubleValue();    }
}
