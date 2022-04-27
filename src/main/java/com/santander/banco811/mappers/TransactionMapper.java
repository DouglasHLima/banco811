package com.santander.banco811.mappers;

import com.santander.banco811.dto.*;
import com.santander.banco811.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {AccountMapper.class}
)
public interface TransactionMapper {

    Transaction toEntity(TransactionRequest transactionRequest);
    TransactionResponse toResponse(Transaction transaction);

}
