package com.appiciel.accountservice.commonapi.command;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.eventhandling.tokenstore.jpa.JpaTokenStore;
public abstract class BaseCommand<T> {
    @TargetAggregateIdentifier
    @Getter
    private T id;

    public BaseCommand(T id) {
        this.id = id;
    }
}
