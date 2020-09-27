package ru.sbrf.payment.client;

import lombok.Getter;
import ru.sbrf.payment.common.Currency;

@Getter

public class AccountDebit extends Account {

    private AccountType accountType = AccountType.ACCOUNT_DEBIT;

    public AccountDebit(long accountNumber, Currency currency, float balance) {
        super(accountNumber, currency, balance);
    }

}
