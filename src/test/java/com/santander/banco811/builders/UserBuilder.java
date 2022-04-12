package com.santander.banco811.builders;

import com.santander.banco811.model.Account;
import com.santander.banco811.model.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserBuilder {

    @Builder.Default
    private String cpf = "12345678";
    @Builder.Default
    private String password = "123456789";
    @Builder.Default
    private String name = "Maria";

    public User toDomain() {
        return User.builder()
                .cpf(this.cpf)
                .password(this.password)
                .name(this.name)
                .build();
    }
}
