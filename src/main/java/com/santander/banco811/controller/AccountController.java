package com.santander.banco811.controller;

import com.santander.banco811.dto.AccountRequest;
import com.santander.banco811.dto.AccountResponse;
import com.santander.banco811.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = "/account",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping(value = "/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse create(
            @PathVariable("userId") Integer userId,
            @Valid @RequestBody AccountRequest accountRequest){
        return accountService.create(userId, accountRequest);
    }
}
