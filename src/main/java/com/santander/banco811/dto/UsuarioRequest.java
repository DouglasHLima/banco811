package com.santander.banco811.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsuarioRequest {
    @CPF
    private String cpf;
    @NotNull
    private String nome;
    @NotNull
    private String senha;
}
