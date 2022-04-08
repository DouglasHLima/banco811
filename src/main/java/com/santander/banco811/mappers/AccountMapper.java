package com.santander.banco811.mappers;

import com.santander.banco811.dto.AccountRequest;
import com.santander.banco811.dto.AccountResponse;
import com.santander.banco811.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toEntity(AccountRequest accountRequest);

    AccountResponse toResponse(Account account);
}
