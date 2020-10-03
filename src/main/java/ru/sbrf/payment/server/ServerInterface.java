package ru.sbrf.payment.server;

import ru.sbrf.payment.common.Payment;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

public interface ServerInterface {
    default Payment makePayment(Payment payment) throws BusinessExceptions {
        return payment;
    }
}
