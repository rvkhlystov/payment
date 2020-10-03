package ru.sbrf.payment.client;

import lombok.Getter;
import ru.sbrf.payment.common.Currency;

@Getter

public class AccountDebit extends Account {

    public AccountDebit(String accountNumber, Currency currency, float balance) {
        super(accountNumber, currency, balance);
    }

}
