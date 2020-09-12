package ru.sbrf.payment.client;

import lombok.Getter;
import ru.sbrf.payment.common.Currency;

@Getter

public class Account {

    private String accountDescription = "счет";
    private long accountNumber;
    private String accountNumberDescription = "номер счета";
    private Currency currency;
    private String currencyDescription = "валюта";
    private float balance;
    private String balanceDescription = "сумма";

    public Account (long accountNumber, Currency currency, float balance) {
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.balance = balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
