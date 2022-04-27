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

@Table(name = "transacao")
@Entity
@Getter @Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "valor")
    private BigDecimal value;
    @Column(name = "tipo_transacao")
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @Column(name = "numero")
    private Integer number;
    @Column(name = "agencia")
    private Integer agency;
    @ManyToOne
    @JoinColumn(name = "conta_id", referencedColumnName = "id")
    private Account account;
    @Column(name = "data_criacao")
    @CreatedDate
    private LocalDateTime creation;
    @Column(name = "data_atualizacao")
    @LastModifiedDate
    private LocalDateTime update;

}
