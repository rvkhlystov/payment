package ru.sbrf.payment.common;

import lombok.Getter;
import ru.sbrf.payment.common.PhoneNumber.PhoneNumberRussian;

@Getter

public class Interaction {

    private String clientNumber;
    private String accountNumber;
    private PhoneNumberRussian phoneNumber;
    private float amount;
    private Currency currency;

    public Interaction(String clientNumber, String accountNumber, PhoneNumberRussian phoneNumber, float amount, Currency currency) {
        this.clientNumber = clientNumber;
        this.accountNumber = accountNumber;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
        this.currency = currency;
    }
}
