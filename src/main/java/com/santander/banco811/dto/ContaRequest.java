package com.santander.banco811.dto;

import com.santander.banco811.model.TipoConta;
import com.santander.banco811.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
public class ContaRequest {
    @NotNull
    private Integer numero;
    @NotNull
    private Integer agencia;
    @NotNull
    private TipoConta tipoConta;
}
