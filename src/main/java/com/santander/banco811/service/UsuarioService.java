package com.santander.banco811.service;

import com.santander.banco811.dto.UsuarioRequest;
import com.santander.banco811.dto.UsuarioResponse;
import com.santander.banco811.model.Usuario;

import java.util.List;

public interface UsuarioService {
    List<UsuarioResponse> getAll(String nome);
    UsuarioResponse create(UsuarioRequest usuarioRequest);
    UsuarioResponse getById(Integer id);
    UsuarioResponse update(UsuarioRequest usuarioRequest, Integer id);
    void delete(Integer id);
}
