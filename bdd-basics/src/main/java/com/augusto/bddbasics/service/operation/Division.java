package com.augusto.bddbasics.service.operation;

public class Division implements MathOperation {
    @Override
    public Number calculate(Number operator, Number operand) {
        if (operand.doubleValue() == 0) {
            throw new ArithmeticException("zero division not permitted");
        }

        return operator.doubleValue() / operand.doubleValue();
    }
}
