package ru.sbrf.payment.app;

import ru.sbrf.payment.common.*;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.server.ServerInterface;

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
        Date date = new Date();
        Payment payment = new Payment(1, date, userData.getClientNumber(), userData.getPhoneNumber(), userData.getAccountNumber(), Currency.RUB, userData.getAmount());
        return payment;

            /*if (Server.makePayment(user) == true) {
                System.out.println("Переведено " + user.getAmount() + " " + user.getCurrency() + " со счета " + user.getAccountNumber() + " на номер телефона +" + user.getPhoneNumber());

            }
            else {
                System.out.println("Платеж не прошел");
            }*/

            //проверка полученных данных
            /*System.out.println("-------------------------------");
            System.out.println(user.getClientNumberDescription() + ": " + user.getClientNumber());
            System.out.println(user.getAccountNumberDescription() + ": " + user.getAccountNumber());
            System.out.println(user.getPhoneNumberDescription() + ": +" + user.getPhoneNumber());
            System.out.println(user.getCurrencyDescription() + ": " + user.getCurrency());
            System.out.println(user.getAmountDescription() + ": " + user.getAmount());*/




    }
}
