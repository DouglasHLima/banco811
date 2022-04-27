package com.santander.banco811.assemblers;

import com.santander.banco811.controller.TransactionController;
import com.santander.banco811.model.Transaction;
import com.santander.banco811.dto.TransactionResponse;
import com.santander.banco811.mappers.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TransactionModelAssembler implements RepresentationModelAssembler<Transaction,TransactionResponse> {

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public TransactionResponse toModel(Transaction entity) {
        TransactionResponse response = transactionMapper.toResponse(entity);
        return response.add(
                linkTo(methodOn(TransactionController.class).getById(entity.getId())).withSelfRel(),
                linkTo(methodOn(TransactionController.class).getAll(Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION)
        );
    }

    @Override
    public CollectionModel<TransactionResponse> toCollectionModel(Iterable<? extends Transaction> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
