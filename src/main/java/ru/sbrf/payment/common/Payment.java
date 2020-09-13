package ru.sbrf.payment.common;

import lombok.Getter;
import ru.sbrf.payment.common.check.CheckPhoneNumber;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

import java.util.Date;

//Реализовать тесты

@Getter

public class Payment extends Object {

    private int numberOperationApp;
    private Date dateOperationApp;
    private String clientNumber;
    private long phoneNumber;
    private long accountNumber;
    private Currency currency;
    private float amount;
    private String amountDescription = "сумма";

    private int numberOperationServer;
    private Date dateOperationServer;
    private boolean statusOperation;
    private DescriptionStatusPayment descriptionStatusPayment;

    public Payment(int numberOperationApp, Date dateOperationApp, String clientNumber, long phoneNumber, long accountNumber, Currency currency, float amount) throws BusinessExceptions {
        this.numberOperationApp = numberOperationApp;
        this.dateOperationApp = dateOperationApp;
        this.clientNumber = clientNumber;
        CheckPhoneNumber.checkPhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.amount = amount;
    }

    public boolean equals(Payment payment) {
        if (payment == null) {
            return false;
        }
        else {
            return ((payment.getClientNumber() == this.getClientNumber()
                    && (payment.getAccountNumber() == this.getAccountNumber())
                    && (payment.getPhoneNumber() == this.getPhoneNumber()))
                    && (payment.getCurrency() == this.getCurrency())
                    && (payment.getAmount() == this.getAmount()
                    && ((this.getDateOperationApp().getTime() - payment.getDateOperationApp().getTime()) < 100000)
            ));
        }

    }

    public void setNumberOperationServer(int numberOperationServer) {
        this.numberOperationServer = numberOperationServer;
    }

    public void setDateOperationServer(Date dateOperationServer) {
        this.dateOperationServer = dateOperationServer;
    }

    public void setStatusOperation(boolean statusOperation) {
        this.statusOperation = statusOperation;
    }

    public void setDescriptionStatusPayment(DescriptionStatusPayment descriptionStatusPayment) {
        this.descriptionStatusPayment = descriptionStatusPayment;
    }
}

