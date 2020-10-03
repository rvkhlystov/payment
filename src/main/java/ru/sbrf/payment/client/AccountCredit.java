package ru.sbrf.payment.client;

import lombok.Getter;
import ru.sbrf.payment.common.Currency;

@Getter

public class AccountCredit extends Account {

    public AccountCredit(long accountNumber, Currency currency, float balance) {
        super(accountNumber, currency, balance);
    }

}
