package com.santander.banco811.dto;

import com.santander.banco811.model.AccountType;
import com.santander.banco811.model.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
public class AccountResponse {
    private Integer id;
    private Integer numero;
    private Integer agencia;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private BigDecimal saldo;
    private AccountType tipoConta;
    private User user;
}
