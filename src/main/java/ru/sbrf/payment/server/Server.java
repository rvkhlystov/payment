package ru.sbrf.payment.server;

import lombok.Getter;
import ru.sbrf.payment.client.User;
import ru.sbrf.payment.common.DescriptionStatusPayment;
import ru.sbrf.payment.common.Payment;

import java.util.Date;
import java.util.HashMap;

@Getter

public class Server implements ServerInterface {

    private String serverName = "server.payment.sbrf.ru"; //имя данного сервера
    private String ip = "127.0.0.1"; //IP-адрес данного сервера
    private int port = 443; //номер порта для взаимодействия с приложением
    private String protocol = "https"; //название протокола взаимодействия

    private HashMap<String, User> clients = new HashMap<>();
    private HashMap<Integer, Payment> payments = new HashMap<>();

    int numberOperationServer = 0;

    public void addClient (String clientNumber, User client) {
        clients.put(clientNumber, client);
    }


    public void makePayment(Payment payment) {
        numberOperationServer += 1;
        //добавить проверку на наличие средств на счете
        //добавить проверку на корректность номера
        boolean statusOperation = true; //после добавления проверок исправить
        DescriptionStatusPayment descriptionStatusPayment = DescriptionStatusPayment.success; //после добавления проверок исправить

        payment.setNumberOperationServer(numberOperationServer);
        payment.setDateOperationServer(new Date());
        payment.setStatusOperation(statusOperation);
        payment.setDescriptionStatusPayment(descriptionStatusPayment);

        payments.put(numberOperationServer, payment);
    }

}
