package ru.sbrf.payment.server.service;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import ru.sbrf.payment.common.Operations.Payment;
import ru.sbrf.payment.common.Operations.StatusPayment;
import ru.sbrf.payment.common.check.CheckFieldsInClass;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.server.Operations.PaymentProcessed;
import ru.sbrf.payment.server.check.CheckPayment;
import ru.sbrf.payment.server.databases.DataBaseClients;
import ru.sbrf.payment.server.databases.DataBasePayments;

import java.util.*;

@Service
@Getter
@Log

public class Server implements ServerInterface {

    @Override
    public PaymentProcessed makePayment(Payment payment, DataBaseClients dataBaseClients, DataBasePayments dataBasePayments) throws BusinessExceptions {

        CheckFieldsInClass.validate(payment);

        //переписать, необходимо будет извлекать последний номер из БД платежей

        int numberOperationServer = dataBasePayments.extractMaxNumberPaymentProcess() + 1;
        StatusPayment statusPayment = StatusPayment.PAYMENTINITIATED;

        //Проверяем корректность платежа
        statusPayment = CheckPayment.checkPayment(payment, dataBaseClients);

        //меняем баланс
        if (statusPayment == StatusPayment.CHECKSUCCESSFULLY) {
            dataBaseClients.getClients().get(payment.getClientNumber()).getAccountsList().get(payment.getAccountNumber()).changeBalance(payment.getAmount());
            statusPayment = StatusPayment.PAYMENTCOMPLETED;
        }
        PaymentProcessed paymentProcessed = new PaymentProcessed(payment, statusPayment, numberOperationServer, new Date());

        return paymentProcessed;
    }

}
