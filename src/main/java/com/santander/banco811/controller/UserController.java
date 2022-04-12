package com.santander.banco811.controller;

import com.santander.banco811.assemblers.UserModelAssembler;
import com.santander.banco811.dto.UserRequest;
import com.santander.banco811.dto.UserResponse;
import com.santander.banco811.model.User;
import com.santander.banco811.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(
        value = "/user",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserModelAssembler userModelAssembler;
    private final PagedResourcesAssembler<UserResponse> pagedAssembler;

    @GetMapping()
    public ResponseEntity<?> getAll(@RequestParam(required = false) String name, Pageable page) {
         val response = pagedAssembler.toModel(userService.getAll(name,page));
        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest userRequest) {
        val created = userService.create(userRequest);
        return ResponseEntity
                .created(created.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(created);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserResponse> update(@PathVariable Integer id, @RequestBody UserRequest userRequest) {
        val updated = userService.update(userRequest, id);
        return ResponseEntity
                .created(updated.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
