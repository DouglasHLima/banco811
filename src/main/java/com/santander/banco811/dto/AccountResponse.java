package com.santander.banco811.dto;

import com.santander.banco811.model.AccountType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Relation(value = "account", collectionRelation = "accounts")
public class AccountResponse extends RepresentationModel<AccountResponse> {
    private Integer id;
    private Integer number;
    private Integer agency;
    private LocalDateTime creation;
    private LocalDateTime update;
    private BigDecimal balance;
    private AccountType accountType;
    private UserResponse user;
    private List<TransactionResponse> transactions;
}
