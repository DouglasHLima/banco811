package com.santander.banco811.assemblers;

import com.santander.banco811.controller.AccountController;
import com.santander.banco811.controller.UserController;
import com.santander.banco811.dto.AccountResponse;
import com.santander.banco811.dto.UserResponse;
import com.santander.banco811.mappers.UserMapper;
import com.santander.banco811.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, UserResponse> {

    @Autowired
    private UserMapper userMapper;
    @Override
    public UserResponse toModel(User entity) {
        UserResponse response = userMapper.toResponse(entity);
        List<Link> links = entity.getAccounts().stream().map(account ->
                linkTo(methodOn(AccountController.class).getById(account.getId())).withRel(IanaLinkRelations.COLLECTION).withRel("accounts")
        ).collect(Collectors.toList());

        response.add(links);
        return response.add(
                linkTo(methodOn(UserController.class).getById(entity.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getAll(null,Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).expand(),
                linkTo(methodOn(UserController.class).getAll(entity.getName(),Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION)

        );
    }

    @Override
    public CollectionModel<UserResponse> toCollectionModel(Iterable<? extends User> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}

