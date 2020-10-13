package ru.sbrf.payment.common.PhoneNumber;

import lombok.Getter;
import ru.sbrf.payment.common.check.CheckPhoneNumber;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;


@Getter

public class PhoneNumberRussian extends PhoneNumber {
    private CodeOfCountry codeOfCountry = CodeOfCountry.RUSSIA;
    private String phoneNumber;

    public PhoneNumberRussian(String phoneNumber) throws BusinessExceptions {
        this.phoneNumber = CheckPhoneNumber.checkPhoneNumber(phoneNumber);
    }
}
