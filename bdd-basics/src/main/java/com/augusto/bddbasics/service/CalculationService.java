package com.augusto.bddbasics.service;

import com.augusto.bddbasics.dto.CalculationRequest;
import com.augusto.bddbasics.dto.CalculationResponse;
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
