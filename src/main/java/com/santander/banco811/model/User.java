package com.santander.banco811.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.santander.banco811.dto.UserRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "usuario")
@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;
    @Column(name = "senha")
    private String senha;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_criacao")
    @CreatedDate
    private LocalDateTime dataCriacao;
    @Column(name = "data_atualizacao")
    @LastModifiedDate
    private LocalDateTime dataAtualizacao;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<Account> accounts;


    public User(UserRequest userRequest){
        this.cpf = userRequest.getCpf();
        this.nome = userRequest.getNome();
        this.senha = userRequest.getSenha();
    }

}
