package ru.sbrf.payment.common.Operations;

import lombok.Getter;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.PhoneNumber.PhoneNumberRussian;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

import java.util.Date;

@Getter

public class PaymentProcessed {
    private int numberOperationApp;
    private Date dateOperationApp;
    private String clientNumber;
    private PhoneNumberRussian phoneNumber;
    private String accountNumber;
    private Currency currency;
    private float amount;

    private StatusPayment statusPayment;
    private int numberOperationServer;
    private Date dateOperationServer;


    public PaymentProcessed(Payment payment, StatusPayment statusPayment, int numberOperationServer, Date dateOperationServer) throws BusinessExceptions {
        this.numberOperationApp = payment.getNumberOperationApp();
        this.dateOperationApp = payment.getDateOperationApp();
        this.clientNumber = payment.getClientNumber();
        //CheckPhoneNumber.checkPhoneNumber(phoneNumber);
        this.phoneNumber = payment.getPhoneNumber();
        this.accountNumber = payment.getAccountNumber();
        this.currency = payment.getCurrency();
        this.amount = payment.getAmount();
        this.statusPayment = statusPayment;
        this.numberOperationServer = numberOperationServer;
        this.dateOperationServer = dateOperationServer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentProcessed)) return false;
        PaymentProcessed that = (PaymentProcessed) o;
        return numberOperationApp == that.numberOperationApp &&
                Float.compare(that.amount, amount) == 0 &&
                numberOperationServer == that.numberOperationServer &&
                dateOperationApp.equals(that.dateOperationApp) &&
                clientNumber.equals(that.clientNumber) &&
                phoneNumber.equals(that.phoneNumber) &&
                accountNumber.equals(that.accountNumber) &&
                currency == that.currency &&
                statusPayment == that.statusPayment &&
                dateOperationServer.equals(that.dateOperationServer);
    }

}
