package com.santander.banco811.service;

import com.santander.banco811.dto.ContaRequest;
import com.santander.banco811.dto.ContaResponse;
import com.santander.banco811.dto.UsuarioRequest;
import com.santander.banco811.dto.UsuarioResponse;
import com.santander.banco811.model.Conta;
import com.santander.banco811.model.Usuario;

import java.util.List;

public interface ContaService {
    List<Conta> getAll();
    ContaResponse create(Integer usuarioId, ContaRequest usuarioRequest);
}
