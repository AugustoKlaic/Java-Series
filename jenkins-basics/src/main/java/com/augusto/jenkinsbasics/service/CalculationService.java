package com.augusto.jenkinsbasics.service;

import com.augusto.jenkinsbasics.dto.CalculationRequest;
import com.augusto.jenkinsbasics.dto.CalculationResponse;
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
