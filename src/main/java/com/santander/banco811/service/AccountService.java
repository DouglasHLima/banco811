package com.santander.banco811.service;

import com.santander.banco811.dto.AccountRequest;
import com.santander.banco811.dto.AccountResponse;
import com.santander.banco811.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAll();
    AccountResponse create(Integer usuarioId, AccountRequest usuarioRequest);
}
