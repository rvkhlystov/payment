package ru.sbrf.payment.server.databases;

import lombok.Getter;
import ru.sbrf.payment.server.Operations.PaymentProcessed;

import java.util.HashMap;

@Getter

public class DataBasePayments {

    private HashMap<Integer, PaymentProcessed> payments = new HashMap<>();

    public void addPayment (PaymentProcessed payment) {
        payments.put(payment.getNumberOperationServer(), payment);
    }


}
