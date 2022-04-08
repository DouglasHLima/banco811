package com.santander.banco811.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UserResponse {
    private Integer id;
    private String cpf;
    private String nome;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}
