package com.appiciel.accountservice.command.aggregate;

import com.appiciel.accountservice.commonapi.command.CreateAccountCommand;
import com.appiciel.accountservice.commonapi.command.CreditAccountCommand;
import com.appiciel.accountservice.commonapi.command.DebitAccountCommand;
import com.appiciel.accountservice.commonapi.enums.AccountStatus;
import com.appiciel.accountservice.commonapi.event.AccountActivatedEvent;
import com.appiciel.accountservice.commonapi.event.AccountCreatedEvent;
import com.appiciel.accountservice.commonapi.event.AccountCreditedEvent;
import com.appiciel.accountservice.commonapi.event.AccountDebitedEvent;
import com.appiciel.accountservice.exception.AmountNegativeException;
import com.appiciel.accountservice.exception.BalanceNotSufficientException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private String currency;
    private AccountStatus status;

    public AccountAggregate() {
        // Required by AXON Framework
    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand) {
        if (createAccountCommand.getInitialBalance() < 0) throw new RuntimeException("Account balance <= 0");
        AggregateLifecycle.apply(new AccountCreatedEvent(
                createAccountCommand.getId(),
                createAccountCommand.getInitialBalance(),
                createAccountCommand.getCurrency(),
                AccountStatus.CREATED));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.accountId = event.getId();
        this.balance = event.getInitialBalance();
        this.status = AccountStatus.CREATED;
        this.currency = event.getCurrency();
        AggregateLifecycle.apply(new AccountActivatedEvent(
                event.getId(),
                AccountStatus.ACTIVATED
        ));
    }

    @EventSourcingHandler
    public void on(AccountActivatedEvent event) {
        this.status = event.getStatus();
    }

    @CommandHandler
    public void handle(CreditAccountCommand creditAccountCommand) throws AmountNegativeException {
        if (creditAccountCommand.getAmount() < 0) throw new AmountNegativeException("Amount should not be negative");
        AggregateLifecycle.apply(new AccountCreditedEvent(
                creditAccountCommand.getId(),
                creditAccountCommand.getAmount(),
                creditAccountCommand.getCurrency()

        ));
    }

    @EventSourcingHandler
    public void on(AccountCreditedEvent event) {
        this.balance += event.getAmount();
    }


    @CommandHandler
    public void handle(DebitAccountCommand debitAccountCommand) throws BalanceNotSufficientException, AmountNegativeException {
        if (debitAccountCommand.getAmount() < 0) throw new AmountNegativeException("Amount should not be negative");
        if (this.balance < debitAccountCommand.getAmount()) throw new BalanceNotSufficientException("Balance not sufficient! =>"+balance );
        AggregateLifecycle.apply(new AccountDebitedEvent(
                debitAccountCommand.getId(),
                debitAccountCommand.getAmount(),
                debitAccountCommand.getCurrency()

        ));
    }

    @EventSourcingHandler
    public void on(AccountDebitedEvent event) {
        this.balance -= event.getAmount();
    }

}
