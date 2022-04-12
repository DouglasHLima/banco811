package com.santander.banco811.service;

import com.santander.banco811.dto.AccountRequest;
import com.santander.banco811.dto.AccountResponse;
import com.santander.banco811.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {
    Page<AccountResponse> getAll(Pageable page);
    AccountResponse create(Integer usuarioId, AccountRequest usuarioRequest);

    AccountResponse getById(Integer id);

    AccountResponse update(Integer id,AccountRequest accountRequest);

    void deleteById(Integer id);
}
