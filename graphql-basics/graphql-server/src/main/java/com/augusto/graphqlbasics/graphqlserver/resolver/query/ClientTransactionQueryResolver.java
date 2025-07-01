package com.augusto.graphqlbasics.graphqlserver.resolver.query;

import com.augusto.graphqlbasics.graphqlserver.dto.ClientProfile;
import com.augusto.graphqlbasics.graphqlserver.dto.TransactionHistory;
import com.augusto.graphqlbasics.graphqlserver.service.ClientTransactionAggregationService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ClientTransactionQueryResolver {

    private final ClientTransactionAggregationService service;

    public ClientTransactionQueryResolver(ClientTransactionAggregationService service) {
        this.service = service;
    }

    @QueryMapping
    public ClientProfile clientById(@Argument Long id) {
        return service.getById(id);
    }

    @QueryMapping
    public List<ClientProfile> allClients() {
        return service.getAll();
    }

    @QueryMapping
    public List<ClientProfile> clientsByTransactionDate(@Argument String date) {
        LocalDate targetDate = LocalDate.parse(date);
        List<TransactionHistory> allTransactions = service.getAllTransactions();

        Set<Long> clientIdsWithTransactionsOnDate = allTransactions.stream()
                .filter(t -> t.transactionDate().equals(targetDate))
                .map(TransactionHistory::clientId)
                .collect(Collectors.toSet());

        return service.getAll().stream()
                .filter(c -> clientIdsWithTransactionsOnDate.contains(c.id()))
                .toList();
    }

    @QueryMapping
    public List<ClientProfile> clientsWithMinTransactionTotal(@Argument BigDecimal minValue) {
        return service.getAll().stream()
                .filter(c -> {
                    BigDecimal total = service.getTransactionsByClientId(c.id()).stream()
                            .map(TransactionHistory::value)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    return total.compareTo(minValue) >= 0;
                })
                .toList();
    }
}
