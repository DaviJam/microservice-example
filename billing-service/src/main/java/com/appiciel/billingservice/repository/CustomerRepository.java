package com.appiciel.billingservice.repository;

import com.appiciel.billingservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
