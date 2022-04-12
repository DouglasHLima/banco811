package com.santander.banco811.repository;

import com.santander.banco811.builders.AccountBuilder;
import com.santander.banco811.builders.UserBuilder;
import com.santander.banco811.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Collections;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void should_be_return_empty_list_when_repository_is_empty() {
        var accounts = accountRepository.findAll();

        Assertions.assertEquals(Collections.emptyList(),accounts);
    }

    @Test
    public void should_return_saved_account_on_update_with_sucess() {
        Account account = AccountBuilder.builder().build().toDomain();
        account.setUser(UserBuilder.builder().build().toDomain());

        Account saved = entityManager.persist(account);
        saved.setNumber(123);
        entityManager.persist(saved);
        Account repositoryResult = accountRepository.findById(saved.getId()).get();

        Assertions.assertEquals(saved.getNumber(),repositoryResult.getNumber());

    }


}