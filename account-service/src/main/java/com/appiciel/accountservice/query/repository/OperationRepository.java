package com.appiciel.accountservice.query.repository;

import com.appiciel.accountservice.query.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation,Long> {
}
