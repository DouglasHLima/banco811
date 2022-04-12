package com.santander.banco811.service.impl;

import com.santander.banco811.assemblers.AccountModelAssembler;
import com.santander.banco811.dto.AccountRequest;
import com.santander.banco811.dto.AccountResponse;
import com.santander.banco811.excptions.AccountNotFoundException;
import com.santander.banco811.mappers.AccountMapper;
import com.santander.banco811.model.Account;
import com.santander.banco811.model.User;
import com.santander.banco811.repository.AccountRepository;
import com.santander.banco811.repository.UserRepository;
import com.santander.banco811.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;
    private final AccountModelAssembler assembler;

    @Override
    public Page<AccountResponse> getAll(Pageable page) {
        return accountRepository.findAll(page).map(assembler::toModel);
    }

    @Override
    public AccountResponse create(Integer userId, AccountRequest accountRequest) {
        User found = userRepository.findById(userId).orElseThrow();
        Account novaAccount = accountMapper.toEntity(accountRequest);
        novaAccount.setUser(found);
        return assembler.toModel(accountRepository.save(novaAccount));
    }

    @Override
    public AccountResponse getById(Integer id) {
        Account found = accountRepository.findById(id).orElseThrow(
                () -> new AccountNotFoundException("Account with Id " + id + " not exists")
        );
        return assembler.toModel(found);
    }

    @Override
    public AccountResponse update(Integer id,AccountRequest accountRequest) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new AccountNotFoundException(String.format(
                        "The Account with id %d does't exists",
                        id))
        );
        BeanUtils.copyProperties(account, accountRequest);
        return assembler.toModel(accountRepository.save(account));
    }

    @Override
    public void deleteById(Integer id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
        } else {
            throw new AccountNotFoundException(String.format("Account with id: %d doesn't exists", id));
        }
    }


}
