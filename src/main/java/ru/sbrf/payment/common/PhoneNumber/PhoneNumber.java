package ru.sbrf.payment.common.PhoneNumber;

import lombok.Getter;

@Getter

public abstract class PhoneNumber {
    private CodeOfCountry codeOfCountry;
    private String phoneNumber;

    public PhoneNumber() {
    }
}
