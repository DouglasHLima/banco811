package com.santander.banco811.dto;

import com.santander.banco811.model.TipoConta;
import com.santander.banco811.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
public class ContaRequest {
    private Integer numero;
    private Integer agencia;
    private BigDecimal saldo;
    private TipoConta tipoConta;
}
