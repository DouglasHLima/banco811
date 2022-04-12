package com.santander.banco811.service;

import com.santander.banco811.dto.AccountRequest;
import com.santander.banco811.dto.AccountResponse;
import com.santander.banco811.dto.TransactionRequest;
import com.santander.banco811.dto.TransactionResponse;
import com.santander.banco811.model.TransactionType;
import com.santander.banco811.projection.TransactionView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {
    Page<TransactionResponse> getAll(Pageable page);

    TransactionResponse getById(Integer id);

    TransactionResponse create(Integer usuarioId, TransactionRequest transactionRequest);

    List<TransactionView> findByTransactionType(TransactionType transactionType);
}
