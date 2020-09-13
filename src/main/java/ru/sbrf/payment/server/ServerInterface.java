package ru.sbrf.payment.server;

import ru.sbrf.payment.common.Payment;

public interface ServerInterface {
    default Payment makePayment(Payment payment) {
        return payment;
    }
}
