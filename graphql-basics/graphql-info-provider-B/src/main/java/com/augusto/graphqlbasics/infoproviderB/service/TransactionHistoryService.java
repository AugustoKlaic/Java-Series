package com.augusto.graphqlbasics.infoproviderB.service;

import com.augusto.graphqlbasics.infoproviderB.dto.TransactionHistory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TransactionHistoryService {

    private final Map<Long, TransactionHistory> fakeDatabase = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public TransactionHistory create(TransactionHistory transaction) {
        long id = idGenerator.getAndIncrement();
        TransactionHistory newTransaction = transaction.withId(id);
        fakeDatabase.put(id, newTransaction);
        return newTransaction;
    }

    public List<TransactionHistory> getAll() {
        return fakeDatabase.values().stream().toList();
    }

    public TransactionHistory getById(Long id) {
        return fakeDatabase.get(id);
    }

    public List<TransactionHistory> getByClientId(Long clientId) {
        return fakeDatabase.values().stream()
                .filter(tx -> Objects.equals(tx.clientId(), clientId))
                .toList();
    }

    public TransactionHistory update(Long id, TransactionHistory updates) {
        TransactionHistory existing = fakeDatabase.get(id);
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

        fakeDatabase.put(id, updated);
        return updated;
    }

    public void delete(Long id) {
        fakeDatabase.remove(id);
    }
}
