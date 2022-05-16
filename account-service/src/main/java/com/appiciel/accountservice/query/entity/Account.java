package com.appiciel.accountservice.query.entity;

import com.appiciel.accountservice.commonapi.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Data
public class Account {
    @Id
    private String id;
    private double balance;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private String currency;
    @OneToMany(mappedBy = "account")
    private Collection<Operation> operations;
}
