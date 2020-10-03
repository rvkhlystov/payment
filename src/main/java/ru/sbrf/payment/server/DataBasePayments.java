package ru.sbrf.payment.server;

import lombok.Getter;
import ru.sbrf.payment.common.Payment;

import java.util.HashMap;

@Getter

public class DataBasePayments {

    private HashMap<Integer, Payment> payments = new HashMap<>();

    public void addPayment (Payment payment) {
        payments.put(payment.getNumberOperationServer(), payment);
    }


}
