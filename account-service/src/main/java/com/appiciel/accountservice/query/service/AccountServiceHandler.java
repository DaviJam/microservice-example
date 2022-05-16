package com.appiciel.accountservice.query.service;

import com.appiciel.accountservice.commonapi.enums.AccountStatus;
import com.appiciel.accountservice.commonapi.enums.OperationType;
import com.appiciel.accountservice.commonapi.event.AccountActivatedEvent;
import com.appiciel.accountservice.commonapi.event.AccountCreatedEvent;
import com.appiciel.accountservice.commonapi.event.AccountCreditedEvent;
import com.appiciel.accountservice.commonapi.event.AccountDebitedEvent;
import com.appiciel.accountservice.commonapi.query.GetAccountByIdQuery;
import com.appiciel.accountservice.commonapi.query.GetAllAccountQuery;
import com.appiciel.accountservice.query.entity.Account;
import com.appiciel.accountservice.query.entity.Operation;
import com.appiciel.accountservice.query.repository.AccountRepository;
import com.appiciel.accountservice.query.repository.OperationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AccountServiceHandler {
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;

    @EventHandler
    public void on(AccountCreatedEvent event){
        log.info("*******************************");
        log.info("AccountCreatedEvent received");
        Account account = new Account();
        account.setId(event.getId());
        account.setBalance(event.getInitialBalance());
        account.setStatus(event.getStatus());
        account.setCurrency(event.getCurrency());
        account.setOperations(null);

        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountActivatedEvent event){
        log.info("*******************************");
        log.info("AccountActivatedEvent received");
        Account account = accountRepository.findById(event.getId()).get();
        account.setStatus(event.getStatus());
    }

    @EventHandler
    public void on(AccountDebitedEvent event){
        log.info("*******************************");
        log.info("AccountDebitedEvent received");
        Account account = accountRepository.findById(event.getId()).get();
        account.setBalance(account.getBalance() - event.getAmount());
        accountRepository.save(account);
        Operation operation = new Operation();
        operation.setAmount(event.getAmount());
        operation.setDate(new Date()); // à ne pas faire
        operation.setOperationType(OperationType.DEBIT);
        operation.setAccount(account);
        operationRepository.save(operation);
    }

    @EventHandler
    public void on(AccountCreditedEvent event){
        log.info("*******************************");
        log.info("AccountCreditedEvent received");
        Account account = accountRepository.findById(event.getId()).get();
        account.setBalance(account.getBalance() + event.getAmount());
        accountRepository.save(account);
        Operation operation = new Operation();
        operation.setAmount(event.getAmount());
        operation.setDate(new Date()); // à ne pas faire
        operation.setOperationType(OperationType.CREDIT);
        operation.setAccount(account);
        operationRepository.save(operation);
    }

    @QueryHandler
    public List<Account> on(GetAllAccountQuery query){
        return accountRepository.findAll();
    }

    @QueryHandler
    public Account on(GetAccountByIdQuery query){
        return accountRepository.findById(query.getId()).get();
    }

}
