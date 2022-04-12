package com.santander.banco811.controller;

import com.santander.banco811.dto.AccountRequest;
import com.santander.banco811.dto.AccountResponse;
import com.santander.banco811.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = "/account",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final PagedResourcesAssembler<AccountResponse> pagedAssembler;


    @GetMapping()
    public ResponseEntity<?> getAll(Pageable page) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal.toString());
        return ResponseEntity.ok(pagedAssembler.toModel(accountService.getAll(page)));

    }

    @GetMapping(value = "/{accountId}")
    public ResponseEntity<AccountResponse> getById(
            @PathVariable("accountId") Integer id) {
        return ResponseEntity.ok(accountService.getById(id));
    }

    @PostMapping(
            value = "/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AccountResponse> create(
            @PathVariable("userId") Integer userId,
            @Valid @RequestBody AccountRequest accountRequest) {
        val response = accountService.create(userId, accountRequest);
        return ResponseEntity
                .created(response.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(response);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AccountResponse> update(
            @PathVariable("id") Integer id,
            @Valid @RequestBody AccountRequest accountRequest
    ) {
        AccountResponse response = accountService.update(id, accountRequest);
        return ResponseEntity
                .created(response.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        accountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}

