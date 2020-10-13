package ru.sbrf.payment.client;

import lombok.Getter;
import ru.sbrf.payment.common.Currency;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter

public class Account {

    @NotNull
    private String accountNumber;
    private String accountNumberDescription = "номер счета";
    private Currency currency;
    private String currencyDescription = "валюта";
    @Min(0)
    private long balance;
    private String balanceDescription = "сумма";

    protected Account(String accountNumber, Currency currency, long balance) {
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.balance = balance;
    }

    public void changeBalance(long amount) {
        this.balance -= amount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getBalance() == account.getBalance() &&
                getAccountNumber().equals(account.getAccountNumber()) &&
                getCurrency() == account.getCurrency();
    }

}
