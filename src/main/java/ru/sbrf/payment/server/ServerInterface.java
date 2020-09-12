package ru.sbrf.payment.server;

import ru.sbrf.payment.common.Payment;

public interface ServerInterface {
    default void makePayment(Payment payment) {
    }
}
