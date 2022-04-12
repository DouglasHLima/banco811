package com.santander.banco811.dto;

import com.santander.banco811.model.TransactionType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Relation(value = "transaction", collectionRelation = "transactions")
public class TransactionResponse extends RepresentationModel<TransactionResponse> {
    private Integer id;
    private BigDecimal value;
    private TransactionType type;
    private Integer number;
    private Integer agency;
    private LocalDateTime creation;
    private LocalDateTime update;
}
