package com.santander.banco811.mappers;

import com.santander.banco811.dto.UserRequest;
import com.santander.banco811.dto.UserResponse;
import com.santander.banco811.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequest userRequest);
    UserResponse toResponse(User user);

}
