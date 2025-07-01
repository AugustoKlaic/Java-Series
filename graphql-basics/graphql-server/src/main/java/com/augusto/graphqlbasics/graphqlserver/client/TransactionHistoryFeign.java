package com.augusto.graphqlbasics.graphqlserver.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "transaction-history", url = "http://localhost:8082")
public interface TransactionHistoryFeign {
}
