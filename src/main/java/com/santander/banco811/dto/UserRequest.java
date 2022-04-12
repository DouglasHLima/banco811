package com.santander.banco811.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserRequest {
//    @CPF
    private String cpf;
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private String login;
}
