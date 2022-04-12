package com.santander.banco811.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
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
@Builder
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;
    @Column(name = "senha")
    private String password;
    @Column(name = "login")
    private String login;
    @Column(name = "nome")
    private String name;

    @Column(name = "data_criacao")
    @CreatedDate
    private LocalDateTime creation;
    @Column(name = "data_atualizacao")
    @LastModifiedDate
    private LocalDateTime update;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<Account> accounts;


}
