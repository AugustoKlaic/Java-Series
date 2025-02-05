package com.augusto.jenkinsbasics.service.operation;

public class Multiplication implements MathOperation {
    @Override
    public Number calculate(Number operator, Number operand) {
        return operator.doubleValue() * operand.doubleValue();
    }
}
