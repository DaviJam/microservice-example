package com.appiciel.billingservice.repository;

import com.appiciel.billingservice.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, String> {
    List<Bill> findByCustomerId(String customerId);
}
