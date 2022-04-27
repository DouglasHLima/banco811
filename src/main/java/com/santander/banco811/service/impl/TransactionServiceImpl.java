package com.santander.banco811.service.impl;

import com.santander.banco811.assemblers.TransactionModelAssembler;
import com.santander.banco811.model.Transaction;
import com.santander.banco811.dto.TransactionRequest;
import com.santander.banco811.dto.TransactionResponse;
import com.santander.banco811.excptions.AccountNotFoundException;
import com.santander.banco811.excptions.TransactionNotFoundException;
import com.santander.banco811.mappers.TransactionMapper;
import com.santander.banco811.model.TransactionType;
import com.santander.banco811.projection.TransactionView;
import com.santander.banco811.repository.AccountRepository;
import com.santander.banco811.repository.TransactionRepository;
import com.santander.banco811.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final TransactionModelAssembler assembler;

    @Override
    public Page<TransactionResponse> getAll(Pageable page) {
        return transactionRepository.findAll(page).map(assembler::toModel);
    }

    @Override
    public TransactionResponse getById(Integer id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() ->
                new TransactionNotFoundException("Transaction not found with id: " + id));
        return assembler.toModel(transaction);
    }

    @Override
    public TransactionResponse create(Integer accountId, TransactionRequest transactionRequest) {
        Transaction newTransaction = transactionMapper.toEntity(transactionRequest);
        newTransaction.setAccount(accountRepository.findById(accountId).orElseThrow( () ->
                new AccountNotFoundException("Account not found with ID: " + accountId)
        ));
        return assembler.toModel(transactionRepository.save(newTransaction));
    }

    @Override
    public List<TransactionView> findByTransactionType(TransactionType transactionType) {
        return transactionRepository.findAllByType(transactionType);
    }

}
