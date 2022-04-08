package com.santander.banco811.service;

import com.santander.banco811.dto.UserRequest;
import com.santander.banco811.dto.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAll(String nome);
    UserResponse create(UserRequest userRequest);
    UserResponse getById(Integer id);
    UserResponse update(UserRequest userRequest, Integer id);
    void delete(Integer id);
}
