package ru.sbrf.payment.client;

import lombok.Getter;
import ru.sbrf.payment.common.Currency;

@Getter

public class AccountCredit extends Account {

    private AccountType accountType = AccountType.ACCOUNT_CREDIT;

    public AccountCredit(long accountNumber, Currency currency, float balance) {
        super(accountNumber, currency, balance);
    }


}