package com.augusto.graphqlbasics.infoproviderB.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionHistory(Long id, Long clientId, BigDecimal value, LocalDate transactionDate, String type) {

    public TransactionHistory withId(Long id) {
        return new TransactionHistory(id, this.clientId, this.value, this.transactionDate, this.type);
    }

    public TransactionHistory withClientId(Long clientId) {
        return new TransactionHistory(this.id, clientId, this.value, this.transactionDate, this.type);
    }

    public TransactionHistory withValue(BigDecimal value) {
        return new TransactionHistory(this.id, this.clientId, value, this.transactionDate, this.type);
    }

    public TransactionHistory withTransactionDate(LocalDate transactionDate) {
        return new TransactionHistory(this.id, this.clientId, this.value, transactionDate, this.type);
    }

    public TransactionHistory withType(String type) {
        return new TransactionHistory(this.id, this.clientId, this.value, this.transactionDate, type);
    }
}
