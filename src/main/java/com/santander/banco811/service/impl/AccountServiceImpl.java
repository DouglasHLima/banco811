package com.santander.banco811.service.impl;

import com.santander.banco811.dto.AccountRequest;
import com.santander.banco811.dto.AccountResponse;
import com.santander.banco811.mappers.AccountMapper;
import com.santander.banco811.model.Account;
import com.santander.banco811.model.User;
import com.santander.banco811.repository.AccountRepository;
import com.santander.banco811.repository.UserRepository;
import com.santander.banco811.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public AccountResponse create(Integer usuarioId, AccountRequest accountRequest) {
        User found = userRepository.findById(usuarioId).orElseThrow();
        Account novaAccount = accountMapper.toEntity(accountRequest);
        novaAccount.setUser(found);
        Account saved = accountRepository.save(novaAccount);
        return accountMapper.toResponse(saved);
    }

}
