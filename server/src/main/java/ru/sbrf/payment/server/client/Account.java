package ru.sbrf.payment.server.client;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.sbrf.payment.common.Currency;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@EqualsAndHashCode

public class Account {

    @NotNull
    private String accountNumber;

    private Currency currency;
    @Min(0)
    private long balance;

    protected Account(String accountNumber, Currency currency, long balance) {
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.balance = balance;
    }

    public void changeBalance(long amount) {
        this.balance -= amount;
    }


/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getBalance() == account.getBalance() &&
                getAccountNumber().equals(account.getAccountNumber()) &&
                getCurrency() == account.getCurrency();
    }*/

}
