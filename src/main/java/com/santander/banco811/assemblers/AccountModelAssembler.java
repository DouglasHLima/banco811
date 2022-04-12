package com.santander.banco811.assemblers;

import com.santander.banco811.controller.AccountController;
import com.santander.banco811.controller.UserController;
import com.santander.banco811.dto.AccountResponse;
import com.santander.banco811.mappers.AccountMapper;
import com.santander.banco811.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AccountModelAssembler implements RepresentationModelAssembler<Account, AccountResponse> {

    @Autowired
    private UserModelAssembler userAssembler;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public AccountResponse toModel(Account entity) {
        AccountResponse response = accountMapper.toResponse(entity);
        response.setUser(userAssembler.toModel(entity.getUser()));
        return response.add(
                linkTo(methodOn(UserController.class).getById(entity.getId())).withRel("user"),
                linkTo(methodOn(AccountController.class).getById(entity.getId())).withSelfRel(),
                linkTo(methodOn(AccountController.class).getAll(Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION)
        );
    }

    @Override
    public CollectionModel<AccountResponse> toCollectionModel(Iterable<? extends Account> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}

