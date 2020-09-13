package ru.sbrf.payment.server;

import lombok.Getter;
import ru.sbrf.payment.client.Account;
import ru.sbrf.payment.client.User;
import ru.sbrf.payment.common.DescriptionStatusPayment;
import ru.sbrf.payment.common.Payment;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.server.check.CheckAccount;
import ru.sbrf.payment.server.check.CheckUser;

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
    boolean statusOperation = true;

    public void addClient (String clientNumber, User client) {
        clients.put(clientNumber, client);
    }

    @Override
    public Payment makePayment(Payment payment) throws BusinessExceptions {
        numberOperationServer += 1;

        //получаем номер клиента из платежки
        String clientNumberFromPayment = payment.getClientNumber();
        //проверяем,есть ли такой клиент в базе
        statusOperation &= CheckUser.checkUser(clientNumberFromPayment, clients);

        //получаем номер счета клиента из платежки
        long accountNumberPayment = payment.getAccountNumber();
        //получаем номер счета клиента из базы
        long accountNumberUser = clients.get(payment.getClientNumber()).getAccount().getAccountNumber();
        //проверяем корректность указанного пользователем счета
        statusOperation &= CheckAccount.checkAccountNumber(accountNumberUser,accountNumberPayment);

        //получаем сумму оплаты из платежа
        float amount = payment.getAmount();

        //получаем баланс счета
        float balance = clients.get(payment.getClientNumber()).getAccount().getBalance();

        //проверяем достаточность средств на счете
        statusOperation &= CheckAccount.checkBalanceForMakeOperation(balance, amount);

        //Меняем баланс
        clients.get(payment.getClientNumber()).getAccount().setBalance(balance - amount);


        //Добавляем платеж в базу
        //Необходимо пересмотреть логику, чтобы реализовать возможность записи в базу,
        //если не пройдены проверки корректности данных в платеже
        DescriptionStatusPayment descriptionStatusPayment = DescriptionStatusPayment.success; //после добавления проверок исправить
        payment.setNumberOperationServer(numberOperationServer);
        payment.setDateOperationServer(new Date());
        payment.setStatusOperation(statusOperation);
        payment.setDescriptionStatusPayment(descriptionStatusPayment);

        payments.put(numberOperationServer, payment);
        return payment;
    }

}
