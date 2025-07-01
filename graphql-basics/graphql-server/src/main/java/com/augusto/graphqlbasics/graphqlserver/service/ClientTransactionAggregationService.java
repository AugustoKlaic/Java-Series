package com.augusto.graphqlbasics.graphqlserver.service;

import com.augusto.graphqlbasics.graphqlserver.client.ClientProfileFeign;
import com.augusto.graphqlbasics.graphqlserver.client.TransactionHistoryFeign;
import com.augusto.graphqlbasics.graphqlserver.dto.ClientProfile;
import com.augusto.graphqlbasics.graphqlserver.dto.TransactionHistory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientTransactionAggregationService {

    private final ClientProfileFeign clientClient;
    private final TransactionHistoryFeign transactionClient;

    public ClientTransactionAggregationService(ClientProfileFeign clientClient, TransactionHistoryFeign transactionClient) {
        this.clientClient = clientClient;
        this.transactionClient = transactionClient;
    }

    public ClientProfile getById(Long id) {
        return clientClient.getById(id);
    }

    public List<ClientProfile> getAll() {
        return clientClient.getAll();
    }

    public List<TransactionHistory> getTransactionsByClientId(Long clientId) {
        return transactionClient.getByClientId(clientId);
    }

    public List<TransactionHistory> getAllTransactions() {
        return transactionClient.getAll();
    }
}
