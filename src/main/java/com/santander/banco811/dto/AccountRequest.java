package com.santander.banco811.dto;

import com.santander.banco811.model.AccountType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class AccountRequest {
    @NotNull
    private Integer numero;
    @NotNull
    private Integer agencia;
    @NotNull
    private AccountType accountType;
}
