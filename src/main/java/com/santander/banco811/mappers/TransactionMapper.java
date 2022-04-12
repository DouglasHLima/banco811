package com.santander.banco811.mappers;

import com.santander.banco811.assemblers.AccountModelAssembler;
import com.santander.banco811.assemblers.TransactionModelAssembler;
import com.santander.banco811.dto.*;
import com.santander.banco811.model.Account;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        uses = {AccountMapper.class}
)
public interface TransactionMapper {

    public abstract Transaction toEntity(TransactionRequest transactionRequest);
    public abstract TransactionResponse toResponse(Transaction transaction);

}
