package com.santander.banco811.builders;


import com.santander.banco811.dto.Transaction;
import com.santander.banco811.model.Account;
import com.santander.banco811.model.AccountType;
import com.santander.banco811.model.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountBuilder {
    @Builder.Default
    private Integer number = 150;
    @Builder.Default
    private Integer agency = 15;
    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;
    @Builder.Default
    private AccountType accountType = AccountType.PF;

    public Account toDomain() {
        return Account.builder()
                .number(this.number)
                .accountType(this.accountType)
                .balance(this.balance)
                .agency(this.agency)
                .build();
    }

}
