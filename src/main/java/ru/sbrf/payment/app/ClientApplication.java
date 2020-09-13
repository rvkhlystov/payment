package ru.sbrf.payment.app;

import ru.sbrf.payment.common.*;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

import java.util.Date;


public class ClientApplication implements ApplicationInterface {
    private String host; //имя хоста,вероятно стоит изменить тип переменной
    private String ip; //ip-адрес,возможно необходимо будет изменить тип переменной
    private int port; //номер порта
    private String protocol; //название протокола, возможно стоит изменить тип

    public boolean connectToServer(){
        //реализовать логику подключения к серверу
        return true;
    }

    public Payment pay(Interaction userData) throws BusinessExceptions {
        Payment payment = new Payment(1, new Date(), userData.getClientNumber(), userData.getPhoneNumber(), userData.getAccountNumber(), Currency.RUB, userData.getAmount());
        return payment;
    }

    @Override
    public void returnStatusPayment(Payment payment) {
        //String qw = payment.getDescriptionStatusPayment().name();
        System.out.println(payment.getDescriptionStatusPayment().name());
        System.out.println("Дата платежа: " + payment.getDateOperationServer());
        System.out.println("Счет отправителя: " + payment.getAccountNumber());
        System.out.println("Номер телефона получателя:" + payment.getPhoneNumber());
        System.out.println("Сумма платежа: " + payment.getAmount() + " " + payment.getCurrency());
    }

}
