package com.santander.banco811.service.impl;

import com.santander.banco811.dto.ContaRequest;
import com.santander.banco811.dto.ContaResponse;
import com.santander.banco811.dto.UsuarioResponse;
import com.santander.banco811.mappers.ContaMapper;
import com.santander.banco811.model.Conta;
import com.santander.banco811.model.Usuario;
import com.santander.banco811.repository.ContaRepository;
import com.santander.banco811.repository.UsuarioRepository;
import com.santander.banco811.service.ContaService;
import com.santander.banco811.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    ContaRepository contaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ContaMapper contaMapper;

    @Override
    public List<Conta> getAll() {
        return contaRepository.findAll();
    }

    @Override
    public ContaResponse create(Integer usuarioId, ContaRequest contaRequest) {
        Usuario found = usuarioRepository.findById(usuarioId).orElseThrow();
        Conta novaConta = contaMapper.toEntity(contaRequest);
        novaConta.setUsuario(found);
        Conta saved = contaRepository.save(novaConta);
        return contaMapper.toResponse(saved);
    }

}
