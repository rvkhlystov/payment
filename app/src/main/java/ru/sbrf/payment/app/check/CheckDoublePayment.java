package ru.sbrf.payment.app.check;

import ru.sbrf.payment.common.Operations.Payment;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

//добавить логирование
public class CheckDoublePayment {
    public static boolean checkDoublePayment(Payment payment, Payment paymentLast) throws BusinessExceptions {
        if (payment.equals(paymentLast) == false) {
            return false;
        }
        else {
            throw new BusinessExceptions("Попытка дублирующего платежа. Если уверены, что хотите повторить платеж, то попробуйте через 5 минут.");
        }
    }
}
