package com.augusto.graphqlbasics.infoproviderB.service;

import com.augusto.graphqlbasics.infoproviderB.dto.TransactionHistory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TransactionHistoryService {

    private static final Map<Long, TransactionHistory> TRANSACTION_HISTORY_SIMULATED_DATABASE = new HashMap<Long, TransactionHistory>() {
        {
            put(1L, new TransactionHistory(1L, 1L, new BigDecimal("1200.00"), LocalDate.of(2024, 12, 15), "crédito"));
            put(2L, new TransactionHistory(2L, 1L, new BigDecimal("250.75"), LocalDate.of(2025, 1, 20), "débito"));
            put(3L, new TransactionHistory(3L, 2L, new BigDecimal("560.00"), LocalDate.of(2025, 2, 3), "crédito"));
            put(4L, new TransactionHistory(4L, 3L, new BigDecimal("400.00"), LocalDate.of(2025, 2, 10), "débito"));
            put(5L, new TransactionHistory(5L, 3L, new BigDecimal("900.00"), LocalDate.of(2025, 3, 5), "crédito"));
            put(6L, new TransactionHistory(6L, 4L, new BigDecimal("150.00"), LocalDate.of(2025, 3, 12), "débito"));
            put(7L, new TransactionHistory(7L, 5L, new BigDecimal("2000.00"), LocalDate.of(2025, 4, 1), "crédito"));
            put(8L, new TransactionHistory(8L, 5L, new BigDecimal("350.00"), LocalDate.of(2025, 4, 7), "débito"));
        }
    };
    private final AtomicLong idGenerator = new AtomicLong(1);

    public TransactionHistory create(TransactionHistory transaction) {
        long id = idGenerator.getAndIncrement();
        TransactionHistory newTransaction = transaction.withId(id);
        TRANSACTION_HISTORY_SIMULATED_DATABASE.put(id, newTransaction);
        return newTransaction;
    }

    public List<TransactionHistory> getAll() {
        return TRANSACTION_HISTORY_SIMULATED_DATABASE.values().stream().toList();
    }

    public TransactionHistory getById(Long id) {
        return TRANSACTION_HISTORY_SIMULATED_DATABASE.get(id);
    }

    public List<TransactionHistory> getByClientId(Long clientId) {
        return TRANSACTION_HISTORY_SIMULATED_DATABASE.values().stream()
                .filter(tx -> Objects.equals(tx.clientId(), clientId))
                .toList();
    }

    public TransactionHistory update(Long id, TransactionHistory updates) {
        TransactionHistory existing = TRANSACTION_HISTORY_SIMULATED_DATABASE.get(id);
        if (existing == null) return null;

        TransactionHistory updated = existing;

        if (updates.clientId() != null)
            updated = updated.withClientId(updates.clientId());

        if (updates.value() != null)
            updated = updated.withValue(updates.value());

        if (updates.transactionDate() != null)
            updated = updated.withTransactionDate(updates.transactionDate());

        if (updates.type() != null)
            updated = updated.withType(updates.type());

        TRANSACTION_HISTORY_SIMULATED_DATABASE.put(id, updated);
        return updated;
    }

    public void delete(Long id) {
        TRANSACTION_HISTORY_SIMULATED_DATABASE.remove(id);
    }
}
