package com.santander.banco811.repository;

import com.santander.banco811.dto.Transaction;
import com.santander.banco811.model.TransactionType;
import com.santander.banco811.projection.TransactionView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<TransactionView> findAllByType(TransactionType transactionType);
}
