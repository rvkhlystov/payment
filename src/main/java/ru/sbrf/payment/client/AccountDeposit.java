package ru.sbrf.payment.client;

import ru.sbrf.payment.common.Currency;

public class AccountDeposit extends Account {
    public AccountDeposit(String accountNumber, Currency currency, long balance) {
        super(accountNumber, currency, balance);
    }
}
