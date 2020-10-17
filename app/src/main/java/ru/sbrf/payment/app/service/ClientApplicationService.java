package ru.sbrf.payment.app.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.sbrf.payment.app.check.CheckDoublePayment;
import ru.sbrf.payment.common.*;
import ru.sbrf.payment.common.Operations.Payment;
import ru.sbrf.payment.common.check.CheckFieldsInClass;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import java.util.Date;

@Service
@Getter

public class ClientApplicationService implements ApplicationInterface {
    private String host; //имя хоста,вероятно стоит изменить тип переменной
    private String ip; //ip-адрес,возможно необходимо будет изменить тип переменной
    private int port; //номер порта
    private String protocol; //название протокола, возможно стоит изменить тип

    private int numberOperationApp = 1;
    private Payment paymentLast;

    public boolean connectToServer(){
        //реализовать логику подключения к серверу
        return true;
    }

    public Payment pay(Interaction userData) throws BusinessExceptions {
        Payment payment = new Payment(this.numberOperationApp, new Date(), userData.getClientNumber(), userData.getPhoneNumber(), userData.getAccountNumber(), Currency.RUB, userData.getAmount());
        CheckFieldsInClass.validate(payment);
        CheckDoublePayment.checkDoublePayment(payment, this.paymentLast);
        this.numberOperationApp += 1;
        this.paymentLast = payment;
        return payment;
    }

}
