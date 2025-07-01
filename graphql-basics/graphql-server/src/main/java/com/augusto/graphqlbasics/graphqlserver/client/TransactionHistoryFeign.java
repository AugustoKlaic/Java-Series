package com.augusto.graphqlbasics.graphqlserver.client;

import com.augusto.graphqlbasics.graphqlserver.dto.TransactionHistory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "transaction-history", url = "http://localhost:8082")
public interface TransactionHistoryFeign {

    @PostMapping("/transaction-history")
    TransactionHistory create(@RequestBody TransactionHistory transaction);

    @GetMapping("/transaction-history")
    List<TransactionHistory> getAll();

    @GetMapping("/transaction-history/{id}")
    TransactionHistory getById(@PathVariable("id") Long id);

    @GetMapping("/transaction-history/client/{clientId}")
    List<TransactionHistory> getByClientId(@PathVariable("clientId") Long clientId);

    @PutMapping("/transaction-history/{id}")
    TransactionHistory update(@PathVariable("id") Long id, @RequestBody TransactionHistory updates);

    @DeleteMapping("/transaction-history/{id}")
    void delete(@PathVariable("id") Long id);
}
