package com.santander.banco811.service.impl;

import com.santander.banco811.dto.UsuarioRequest;
import com.santander.banco811.dto.UsuarioResponse;
import com.santander.banco811.mappers.UsuarioMapper;
import com.santander.banco811.model.Usuario;
import com.santander.banco811.repository.UsuarioRepository;
import com.santander.banco811.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    UsuarioMapper usuarioMapper;

    @Override
    public List<UsuarioResponse> getAll(String nome) {
        return nome != null ?
                usuarioRepository.findByNome(nome).stream()
                        .map(usuarioMapper::toResponse).collect(Collectors.toList())
                : usuarioRepository.findAll().stream()
                        .map(usuarioMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public UsuarioResponse create(UsuarioRequest usuarioRequest) {
        Usuario usuario = usuarioMapper.toEntity(usuarioRequest);
        Usuario saved = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(saved);
    }

    @Override
    public UsuarioResponse getById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        return usuarioMapper.toResponse(usuario);
    }

    @Override
    public UsuarioResponse update(UsuarioRequest usuarioRequest, Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(usuario,usuarioRequest);
        Usuario saved = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(saved);
    }

    @Override
    public void delete(Integer id) {
        var usuario = usuarioRepository.findById(id).orElseThrow();

        usuarioRepository.delete(usuario);
    }
}
