package com.santander.banco811.dto;

import com.santander.banco811.model.TipoConta;
import com.santander.banco811.model.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
public class ContaResponse {
    private Integer id;
    private Integer numero;
    private Integer agencia;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private BigDecimal saldo;
    private TipoConta tipoConta;
    private Usuario usuario;
}
