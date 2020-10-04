package ru.sbrf.payment.server;

import ru.sbrf.payment.common.Operations.Payment;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.common.Operations.PaymentProcessed;

public interface ServerInterface {
    default PaymentProcessed makePayment(Payment payment, DataBaseClients dataBaseClients, DataBasePayments dataBasePayments) throws BusinessExceptions {
        return null;
    }
}
