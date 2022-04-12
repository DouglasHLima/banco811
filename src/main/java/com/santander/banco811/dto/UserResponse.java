package com.santander.banco811.dto;

import com.santander.banco811.assemblers.UserModelAssembler;
import com.santander.banco811.controller.UserController;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.mediatype.hal.HalLinkDiscoverer;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Relation(value = "user", collectionRelation = "users")
public class UserResponse extends RepresentationModel<UserResponse> {
    private Integer id;
    private String cpf;
    private String name;
    private LocalDateTime creation;
    private LocalDateTime update;
}
