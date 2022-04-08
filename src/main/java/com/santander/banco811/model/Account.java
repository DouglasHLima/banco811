package com.santander.banco811.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "conta")
@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "numero")
    private Integer number;
    @Column(name = "agencia")
    private Integer agency;
    @Column(name = "data_criacao")
    @CreatedDate
    private LocalDateTime criation;
    @Column(name = "data_atualizacao")
    @LastModifiedDate
    private LocalDateTime update;
    @Column(name = "saldo")
    private BigDecimal balance;
    @Column(name = "tipo_conta")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private User user;
}
