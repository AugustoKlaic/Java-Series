package com.augusto.unittesting.service;

import java.util.Arrays;

public enum OperationEnum {
    ADDITION(new Addition()),
    SUBTRACTION(new Subtraction()),
    DIVISION(new Division()),
    MULTIPLICATION(new Multiplication());
    private final MathOperation operation;

    OperationEnum(MathOperation operation) {
        this.operation = operation;
    }

    public MathOperation getOperation() {
        return operation;
    }

    public static MathOperation getOperation(String mathOperation) {
        return Arrays.stream(OperationEnum.values())
                .filter(op -> op.name().equalsIgnoreCase(mathOperation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Operation: " + mathOperation))
                .getOperation();
    }
}
