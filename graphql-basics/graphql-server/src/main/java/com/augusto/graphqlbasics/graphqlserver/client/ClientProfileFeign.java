package com.augusto.graphqlbasics.graphqlserver.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "client-profile", url = "http://localhost:8081")
public interface ClientProfileFeign {
}
