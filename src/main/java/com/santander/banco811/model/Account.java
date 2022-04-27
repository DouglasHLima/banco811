package com.santander.banco811.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "conta")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private LocalDateTime creation;
    @Column(name = "data_atualizacao")
    @LastModifiedDate
    private LocalDateTime update;
    @Column(name = "saldo")
    private BigDecimal balance = BigDecimal.ZERO;
    @Column(name = "tipo_conta")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<Transaction> transactions;
}
