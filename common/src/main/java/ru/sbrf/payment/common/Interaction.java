package ru.sbrf.payment.common;

import lombok.Getter;
import ru.sbrf.payment.common.PhoneNumber.PhoneNumberRussian;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter

public class Interaction {

    @NotNull
    @Size(max = 10, min = 1)
    private String clientNumber;
    @NotNull
    private String accountNumber;
    @NotNull
    @Size(max = 10, min = 10)
    private PhoneNumberRussian phoneNumber;
    @Min(0)
    private long amount;
    private Currency currency;

    public Interaction(String clientNumber, String accountNumber, PhoneNumberRussian phoneNumber, long amount, Currency currency) {
        this.clientNumber = clientNumber;
        this.accountNumber = accountNumber;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
        this.currency = currency;
    }
}
