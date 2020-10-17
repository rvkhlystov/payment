package ru.sbrf.payment.app.service;

import ru.sbrf.payment.common.Operations.Payment;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;


public interface ApplicationInterface {
    default void returnStatusPayment(Payment payment) {
    }

    default Payment pay(Interaction userData) throws BusinessExceptions {
        return null;
    }
}
