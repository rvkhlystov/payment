package ru.sbrf.payment.app;

import ru.sbrf.payment.app.check.CheckDoublePayment;
import ru.sbrf.payment.common.*;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

import java.util.Date;

//Реализовать тесты

public class ClientApplication implements ApplicationInterface {
    private String host; //имя хоста,вероятно стоит изменить тип переменной
    private String ip; //ip-адрес,возможно необходимо будет изменить тип переменной
    private int port; //номер порта
    private String protocol; //название протокола, возможно стоит изменить тип

    private int numberOperationApp = 1;
    Payment paymentLast;

    public boolean connectToServer(){
        //реализовать логику подключения к серверу
        return true;
    }

    public Payment pay(Interaction userData) throws BusinessExceptions {
        Payment payment = new Payment(numberOperationApp, new Date(), userData.getClientNumber(), userData.getPhoneNumber(), userData.getAccountNumber(), Currency.RUB, userData.getAmount());
        CheckDoublePayment.checkDoublePayment(payment, paymentLast);
        numberOperationApp += 1;
        paymentLast = payment;
        return payment;
    }

    @Override
    public void returnStatusPayment(Payment payment) {
        System.out.println(payment.getDescriptionStatusPayment().name());
        System.out.println("Дата платежа: " + payment.getDateOperationServer());
        System.out.println("Счет отправителя: " + payment.getAccountNumber());
        System.out.println("Номер телефона получателя:" + payment.getPhoneNumber());
        System.out.println("Сумма платежа: " + payment.getAmount() + " " + payment.getCurrency());
    }

}
