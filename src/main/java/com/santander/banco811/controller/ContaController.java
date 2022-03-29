package com.santander.banco811.controller;

import com.santander.banco811.dto.ContaRequest;
import com.santander.banco811.dto.ContaResponse;
import com.santander.banco811.mappers.ContaMapper;
import com.santander.banco811.model.Conta;
import com.santander.banco811.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("conta")
public class ContaController {

    @Autowired
    ContaService contaService;

    @PostMapping(value = "/{ûserId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ContaResponse criarConta(
            @PathVariable("ûserId") Integer userId,
            @Valid @RequestBody ContaRequest contaRequest){
        return contaService.create(userId, contaRequest);
    }


}
