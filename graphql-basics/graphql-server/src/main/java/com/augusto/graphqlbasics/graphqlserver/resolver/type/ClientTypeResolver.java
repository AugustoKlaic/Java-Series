package com.augusto.graphqlbasics.graphqlserver.resolver.type;

import com.augusto.graphqlbasics.graphqlserver.dto.ClientProfile;
import com.augusto.graphqlbasics.graphqlserver.dto.TransactionHistory;
import com.augusto.graphqlbasics.graphqlserver.service.ClientTransactionAggregationService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ClientTypeResolver {
    private final ClientTransactionAggregationService service;

    public ClientTypeResolver(ClientTransactionAggregationService service) {
        this.service = service;
    }

    @SchemaMapping(typeName = "ClientProfile", field = "transactions")
    public List<TransactionHistory> transactions(ClientProfile profile) {
        return service.getTransactionsByClientId(profile.id());
    }

    @SchemaMapping(typeName = "ClientProfile", field = "totalTransactionAmount")
    public BigDecimal totalTransactionAmount(ClientProfile profile) {
        return service.getTransactionsByClientId(profile.id())
                .stream()
                .map(TransactionHistory::value)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @SchemaMapping(typeName = "ClientProfile", field = "transactionsByType")
    public List<TransactionHistory> transactionsByType(ClientProfile profile, @Argument String type) {
        return service.getTransactionsByClientId(profile.id())
                .stream()
                .filter(t -> t.type().equalsIgnoreCase(type))
                .toList();
    }


}
