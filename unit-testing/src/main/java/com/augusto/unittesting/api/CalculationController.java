package com.augusto.unittesting.api;


import com.augusto.buildgradle.dto.CalculationRequest;
import com.augusto.buildgradle.dto.CalculationResponse;
import com.augusto.buildgradle.service.CalculationService;
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
