package ru.sbrf.payment.server.client;

import ru.sbrf.payment.common.Currency;

public class AccountDeposit extends Account {
    public AccountDeposit(String accountNumber, Currency currency, long balance) {
        super(accountNumber, currency, balance);
    }
}
