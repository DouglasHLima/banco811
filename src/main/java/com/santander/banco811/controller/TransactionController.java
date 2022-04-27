package com.santander.banco811.controller;

import com.santander.banco811.dto.TransactionRequest;
import com.santander.banco811.dto.TransactionResponse;
import com.santander.banco811.model.TransactionType;
import com.santander.banco811.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = "/transaction",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final PagedResourcesAssembler<TransactionResponse> pagedAssembler;


    @GetMapping(value = "/searchByType")
    public ResponseEntity<?> getViewByAccountType(@RequestParam TransactionType transactionType) {
        val results = transactionService.findByTransactionType(transactionType);
        return ResponseEntity.ok(results);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TransactionResponse> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(transactionService.getById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll(Pageable page) {
        Page<TransactionResponse> found = transactionService.getAll(page);
        return ResponseEntity.ok(pagedAssembler.toModel(found));
    }

    @PostMapping(
            value = "/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TransactionResponse> create(
            @PathVariable("userId") Integer id,
            @Valid @RequestBody TransactionRequest transactionRequest
    ) {
        TransactionResponse created = transactionService.create(id, transactionRequest);
        return ResponseEntity
                .created(created.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(created);
    }


}
