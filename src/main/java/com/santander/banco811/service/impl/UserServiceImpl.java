package com.santander.banco811.service.impl;

import com.santander.banco811.dto.UserRequest;
import com.santander.banco811.dto.UserResponse;
import com.santander.banco811.mappers.UserMapper;
import com.santander.banco811.model.User;
import com.santander.banco811.repository.UserRepository;
import com.santander.banco811.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserResponse> getAll(String nome) {
        return nome != null ?
                userRepository.findByNome(nome).stream()
                        .map(userMapper::toResponse).collect(Collectors.toList())
                : userRepository.findAll().stream()
                        .map(userMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public UserResponse create(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    @Override
    public UserResponse getById(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse update(UserRequest userRequest, Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(user, userRequest);
        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    @Override
    public void delete(Integer id) {
        var usuario = userRepository.findById(id).orElseThrow();

        userRepository.delete(usuario);
    }
}
