package com.augusto.jenkinsbasics.api;

import com.augusto.jenkinsbasics.dto.CalculationRequest;
import com.augusto.jenkinsbasics.dto.CalculationResponse;
import com.augusto.jenkinsbasics.service.CalculationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculate")
public class CalculationController {

    private final CalculationService service;

    public CalculationController(CalculationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CalculationResponse> calculate(@RequestBody CalculationRequest request) {
        return ResponseEntity.ok(service.doCalculation(request));
    }
}
