package com.santander.banco811.repository;

import com.santander.banco811.model.AccountType;
import com.santander.banco811.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findBySaldoLessThan(BigDecimal saldo);
    List<Account> findBySaldoLessThanEqual(BigDecimal saldo);
    List<Account> findBySaldoGreaterThan(BigDecimal saldo);
    List<Account> findBySaldoGreaterThanEqual(BigDecimal saldo);

    List<Account> findBySaldoBetween(BigDecimal saldoInicial, BigDecimal saldoFinal);
    List<Account> findBySaldoIn(List<BigDecimal> saldos);

    List<Account> findByTipoContaAndSaldoBetweenOrderBySaldo(AccountType accountType, BigDecimal saldoInicial, BigDecimal saldoFinal);

    List<Account> findByUsuario_cpf(String cpf);

    Boolean existsByTipoConta(AccountType accountType);

    @Query("select c from Conta c " +
            "where (c.tipoConta = :tipoConta and c.usuario.cpf = :cpf) " +
            "or (c.tipoConta = :tipoConta or c.saldo = :saldo)")
    List<Account> findByTipoContaAndCpfOrTipoContaAndSaldo(
            @Param("tipoConta") AccountType accountType,
            @Param("cpf") String cpf,
            @Param("saldo") BigDecimal saldo
    );

    @Query(value = "select * from conta c" +
            "where (c.tipo_conta = :tipoConta AND" +
            "c.data_criacao >= :dataCriacao)" +
            "OR c.saldo = :saldo ", nativeQuery = true)
    List<Account> findByDataCriacaoAndTipoContaOrSaldo(
            @Param("dataCriacao") LocalDateTime dataCriacao,
            @Param("tipoConta") LocalDateTime tipoConta,
            @Param("saldo") BigDecimal saldo
    );
}
