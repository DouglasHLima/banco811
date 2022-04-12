package com.santander.banco811.dto;

import com.santander.banco811.model.TransactionType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequest {
    @NotNull @Positive
    private BigDecimal value;
    @NotNull
    private TransactionType type;
    @NotNull
    private Integer number;
    @NotNull
    private Integer agency;
}
