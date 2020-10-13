package ru.sbrf.payment.common.Operations;

import lombok.Getter;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.PhoneNumber.PhoneNumberRussian;

import javax.validation.constraints.Min;
import java.util.Date;

//Реализовать тесты

@Getter

public class Payment {

    private int numberOperationApp;
    private Date dateOperationApp;
    private String clientNumber;
    private PhoneNumberRussian phoneNumber;
    private String accountNumber;
    private Currency currency;
    @Min(0)
    private long amount;
    private String amountDescription = "сумма";

    public Payment(int numberOperationApp, Date dateOperationApp, String clientNumber, PhoneNumberRussian phoneNumber, String accountNumber, Currency currency, long amount) {
        this.numberOperationApp = numberOperationApp;
        this.dateOperationApp = dateOperationApp;
        this.clientNumber = clientNumber;
        //CheckPhoneNumber.checkPhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.amount = amount;
    }

        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return getAmount() == payment.getAmount() &&
                //getDateOperationApp().equals(payment.getDateOperationApp()) &&
                (getDateOperationApp().getTime() - payment.getDateOperationApp().getTime()) < 100000 &&
                getClientNumber().equals(payment.getClientNumber()) &&
                getPhoneNumber().equals(payment.getPhoneNumber()) &&
                getAccountNumber().equals(payment.getAccountNumber()) &&
                getCurrency() == payment.getCurrency();
    }

}

