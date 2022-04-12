package com.santander.banco811.service;

import com.santander.banco811.dto.UserRequest;
import com.santander.banco811.dto.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<UserResponse> getAll(String nome, Pageable page);
    UserResponse create(UserRequest userRequest);
    UserResponse getById(Integer id);
    UserResponse update(UserRequest userRequest, Integer id);
    void delete(Integer id);
}
