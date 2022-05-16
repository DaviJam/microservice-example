package com.appiciel.accountservice.command.controller;

import com.appiciel.accountservice.commonapi.command.CreateAccountCommand;
import com.appiciel.accountservice.commonapi.command.CreditAccountCommand;
import com.appiciel.accountservice.commonapi.command.DebitAccountCommand;
import com.appiciel.accountservice.dto.CreateAccountRequestDTO;
import com.appiciel.accountservice.dto.CreditAccountRequestDTO;
import com.appiciel.accountservice.dto.DebitAccountRequestDTO;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/commands/account")
@AllArgsConstructor
public class AccountCommandController {

    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping(path = "/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountRequestDTO createAccountDTO){

        CompletableFuture<String> commandResponse = commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                createAccountDTO.getInitialBalance(),
                createAccountDTO.getCurrency()
        ));

        return commandResponse;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> ExceptionHandler(Exception exception){
        ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }

    @GetMapping("/eventStore/{accountId}")
    public Stream eventStore(@PathVariable String accountId){
        return eventStore.readEvents(accountId).asStream();

    }

    @PutMapping(path = "/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDTO creditAccountDTO){
        CompletableFuture<String> commandResponse = commandGateway.send(new CreditAccountCommand(
                creditAccountDTO.getAccountId(),
                creditAccountDTO.getAmount(),
                creditAccountDTO.getCurrency()
        ));

        return commandResponse;
    }

    @PutMapping(path = "/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountRequestDTO debitAccountDTO){
        CompletableFuture<String> commandResponse = commandGateway.send(new DebitAccountCommand(
                debitAccountDTO.getAccountId(),
                debitAccountDTO.getAmount(),
                debitAccountDTO.getCurrency()
        ));

        return commandResponse;
    }

}
