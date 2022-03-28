package com.santander.banco811.mappers;

import com.santander.banco811.dto.ContaRequest;
import com.santander.banco811.dto.ContaResponse;
import com.santander.banco811.model.Conta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContaMapper {

    Conta toEntity(ContaRequest contaRequest);

    ContaResponse toResponse(Conta conta);
}
