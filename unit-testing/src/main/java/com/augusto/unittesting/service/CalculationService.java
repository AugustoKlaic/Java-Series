package com.augusto.unittesting.service;

import com.augusto.unittesting.dto.CalculationRequest;
import com.augusto.unittesting.dto.CalculationResponse;
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
