package ru.sbrf.payment.client;

import lombok.Getter;
import ru.sbrf.payment.common.Currency;

@Getter

public class Account {

    private String accountNumber;
    private String accountNumberDescription = "номер счета";
    private Currency currency;
    private String currencyDescription = "валюта";
    private float balance;
    private String balanceDescription = "сумма";

    protected Account(String accountNumber, Currency currency, float balance) {
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.balance = balance;
    }

    public void changeBalance(float amount) {
        this.balance -= amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Float.compare(account.getBalance(), getBalance()) == 0 &&
                getAccountNumber().equals(account.getAccountNumber()) &&
                getCurrency() == account.getCurrency();
    }


}
