package com.augusto.unittesting.service;

import com.augusto.buildgradle.dto.CalculationRequest;
import com.augusto.buildgradle.dto.CalculationResponse;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    public CalculationResponse doCalculation(CalculationRequest request) {
        return new CalculationResponse(
                OperationEnum.getOperation(request.getMathOperation())
                        .calculate(request.getOperator(),
                                   request.getOperand()));
    }
}
