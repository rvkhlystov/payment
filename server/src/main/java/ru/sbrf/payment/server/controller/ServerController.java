package ru.sbrf.payment.server.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.Operations.Payment;
import ru.sbrf.payment.common.Operations.StatusPayment;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.server.databases.DataBaseClients;
import ru.sbrf.payment.server.databases.DataBasePayments;
import ru.sbrf.payment.server.Operations.PaymentProcessed;
import ru.sbrf.payment.server.service.Server;
import ru.sbrf.payment.server.client.AccountCredit;
import ru.sbrf.payment.server.client.AccountDebit;
import ru.sbrf.payment.server.client.Client;

@RestController
@AllArgsConstructor

public class ServerController {
    private Server server;

    @GetMapping("/server")
    public String getServerInfo() {
        return "It's server";
    }

    @PostMapping("/server/operations")
    public StatusPayment makePayment (
            @RequestBody Payment payment
            ) throws BusinessExceptions {

        DataBaseClients dataBaseClients = new DataBaseClients();
        DataBasePayments dataBasePayments = new DataBasePayments();

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000)));

        //обрабатываем платеж
        PaymentProcessed paymentProcessed = server.makePayment(payment, dataBaseClients, dataBasePayments);

        return paymentProcessed.getStatusPayment();
    }

}
