package com.appiciel.accountservice.query.repository;

import com.appiciel.accountservice.query.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
