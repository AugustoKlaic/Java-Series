package com.augusto.graphqlbasics.infoproviderB.controller;

import com.augusto.graphqlbasics.infoproviderB.dto.TransactionHistory;
import com.augusto.graphqlbasics.infoproviderB.service.TransactionHistoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/transaction-history")
public class TransactionHistoryController {

    private final TransactionHistoryService service;

    public TransactionHistoryController(TransactionHistoryService service) {
        this.service = service;
    }

    @PostMapping
    public TransactionHistory create(@RequestBody TransactionHistory transaction) {
        return service.create(transaction);
    }

    @GetMapping
    public List<TransactionHistory> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public TransactionHistory getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/client/{clientId}")
    public List<TransactionHistory> getByClientId(@PathVariable Long clientId) {
        return service.getByClientId(clientId);
    }

    @PutMapping("/{id}")
    public TransactionHistory update(@PathVariable Long id, @RequestBody TransactionHistory updates) {
        return service.update(id, updates);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
