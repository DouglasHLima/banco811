package com.santander.banco811.dto;

import com.santander.banco811.model.AccountType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class AccountRequest {
    private Integer number;
    @NotNull
    private Integer agency;
    @NotNull
    private AccountType accountType;
}
