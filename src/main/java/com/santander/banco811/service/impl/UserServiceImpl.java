package com.santander.banco811.service.impl;

import com.santander.banco811.assemblers.UserModelAssembler;
import com.santander.banco811.dto.UserRequest;
import com.santander.banco811.dto.UserResponse;
import com.santander.banco811.mappers.UserMapper;
import com.santander.banco811.model.User;
import com.santander.banco811.repository.UserRepository;
import com.santander.banco811.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserModelAssembler assembler;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserResponse> getAll(String name, Pageable page) {
        return name != null ?
                userRepository.findByName(name,page)
                        .map(assembler::toModel)
                : userRepository.findAll(page)
                        .map(assembler::toModel);
    }

    @Override
    public UserResponse create(UserRequest userRequest) {
        var passwordEncrypted = passwordEncoder.encode(userRequest.getPassword());
        User user = userMapper.toEntity(userRequest);
        user.setPassword(passwordEncrypted);
        User saved = userRepository.save(user);
        return assembler.toModel(saved);
    }

    @Override
    public UserResponse getById(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return assembler.toModel(user);
    }

    @Override
    public UserResponse update(UserRequest userRequest, Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(user, userRequest);
        User saved = userRepository.save(user);
        return assembler.toModel(saved);
    }

    @Override
    public void delete(Integer id) {
        var usuario = userRepository.findById(id).orElseThrow();
        userRepository.delete(usuario);
    }
}
