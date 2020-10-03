package ru.sbrf.payment.app;

import ru.sbrf.payment.common.Payment;

public interface ApplicationInterface {
    default void returnStatusPayment(Payment payment) {
    }
}
