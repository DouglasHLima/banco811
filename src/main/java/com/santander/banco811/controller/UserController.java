package com.santander.banco811.controller;

import com.santander.banco811.dto.UserRequest;
import com.santander.banco811.dto.UserResponse;
import com.santander.banco811.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(
        value = "/user",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAll(@RequestParam(required = false) String name) {
        return userService.getAll(name);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@Valid @RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse update(@PathVariable Integer id, @RequestBody UserRequest userRequest) {
        return userService.update(userRequest, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }
}
