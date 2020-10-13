package ru.sbrf.payment.server;

import lombok.Getter;
import ru.sbrf.payment.common.Operations.PaymentProcessed;

import java.util.HashMap;

@Getter

public class DataBasePayments {

    private HashMap<Integer, PaymentProcessed> payments = new HashMap<>();

    public void addPayment (PaymentProcessed payment) {
        payments.put(payment.getNumberOperationServer(), payment);
    }


}
