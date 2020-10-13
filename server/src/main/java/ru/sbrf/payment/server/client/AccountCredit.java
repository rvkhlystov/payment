package ru.sbrf.payment.server.client;

import lombok.Getter;
import ru.sbrf.payment.common.Currency;

@Getter

public class AccountCredit extends Account {

    public AccountCredit(String accountNumber, Currency currency, long balance) {
        super(accountNumber, currency, balance);
    }

}
