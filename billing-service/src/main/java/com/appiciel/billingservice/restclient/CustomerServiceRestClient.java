package com.appiciel.billingservice.restclient;

import com.appiciel.billingservice.domain.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="CUSTOMER-SERVICE")
public interface CustomerServiceRestClient {
    @GetMapping(path="/api/v1/customers/{id}")
    Customer customerById(@PathVariable String id);

    @GetMapping(path = "/api/v1/customers")
    List<Customer> customers();
}
