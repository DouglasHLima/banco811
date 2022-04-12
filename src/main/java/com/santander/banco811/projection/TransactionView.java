package com.santander.banco811.projection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.santander.banco811.model.TransactionType;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public interface TransactionView {
    BigDecimal getValue();

    TransactionType getType();

    @JsonProperty(value = "Account")
    @Value("#{'Acc.:' + target.number + ' ' + 'ag.:' + target.agency}")
    String getNumberAgency();
}
