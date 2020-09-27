package ru.sbrf.payment.client;

import lombok.Getter;
import ru.sbrf.payment.common.Currency;

@Getter

public class AccountDebit extends Account {

    public AccountDebit(long accountNumber, Currency currency, float balance) {
        super(accountNumber, currency, balance);
    }

}
