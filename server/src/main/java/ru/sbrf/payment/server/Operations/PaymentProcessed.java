package ru.sbrf.payment.server.Operations;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.Operations.Payment;
import ru.sbrf.payment.common.Operations.StatusPayment;
import ru.sbrf.payment.common.PhoneNumber.PhoneNumberRussian;
import ru.sbrf.payment.common.check.CheckFieldsInClass;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

import javax.validation.constraints.Min;
import java.util.Date;

@Getter
@EqualsAndHashCode
@AllArgsConstructor

public class PaymentProcessed {
    private int numberOperationApp;
    private Date dateOperationApp;
    private String clientNumber;
    private PhoneNumberRussian phoneNumber;
    private String accountNumber;
    private Currency currency;
    @Min(0)
    private long amount;
    private StatusPayment statusPayment;
    private int numberOperationServer;
    private Date dateOperationServer;


    public PaymentProcessed(Payment payment, StatusPayment statusPayment, int numberOperationServer, Date dateOperationServer) throws BusinessExceptions {
        CheckFieldsInClass.validate(payment);
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

/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentProcessed)) return false;
        PaymentProcessed that = (PaymentProcessed) o;
        return numberOperationApp == that.numberOperationApp &&
                amount == that.amount &&
                numberOperationServer == that.numberOperationServer &&
                dateOperationApp.equals(that.dateOperationApp) &&
                clientNumber.equals(that.clientNumber) &&
                phoneNumber.equals(that.phoneNumber) &&
                accountNumber.equals(that.accountNumber) &&
                currency == that.currency &&
                statusPayment == that.statusPayment &&
                dateOperationServer.equals(that.dateOperationServer);
    }*/

}
