package ru.sbrf.payment.server.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.Operations.CreatorTransferPayment;
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

import java.text.ParseException;
import java.util.HashMap;

@RestController
@AllArgsConstructor
@Log

public class ServerController {
    private Server server;

    @GetMapping("/server")
    public String getServerInfo() {
        log.info("Request from outside=/server, Response=It's server");
        return "It's server";
    }

    @PostMapping("/server/operations")
    public StatusPayment makePayment (
            //@RequestBody Payment payment
            @RequestBody HashMap paymentTemp //обходная реализация
            ) throws BusinessExceptions, ParseException {
        log.info("Request /server/operations/" + paymentTemp.toString());

        //создаем базу данных - необходимо заменить реализацию
        DataBaseClients dataBaseClients = new DataBaseClients();
        DataBasePayments dataBasePayments = new DataBasePayments();
        //добавляем в базы данных двух клиентов - исключить после реализации БД
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000)));

        //Обходная реализация
        Payment payment = CreatorTransferPayment.createPaymentFromTransferPayment(paymentTemp);
        //конец обходной реализации
        log.info("CreatorTransferPayment.createPaymentFromTransferPayment(paymentTemp) " +
                "NumberOperationApp=" + payment.getNumberOperationApp() +
                ", DateOperationApp=" + payment.getDateOperationApp() +
                ", ClientNumber=" + payment.getClientNumber() +
                ", AccountNumber=" + payment.getAccountNumber() +
                ", Amount=" + payment.getAmount() +
                ", Currency=" + payment.getCurrency() +
                ", PhoneNumber=" +payment.getPhoneNumber().getPhoneNumber()
        );
        //обрабатываем платеж
        PaymentProcessed paymentProcessed = server.makePayment(payment, dataBaseClients, dataBasePayments);
        log.info("server.makePayment " +
                "NumberOperationApp=" + paymentProcessed.getNumberOperationApp() +
                ", DateOperationApp=" + paymentProcessed.getDateOperationApp() +
                ", ClientNumber=" + paymentProcessed.getClientNumber() +
                ", AccountNumber=" + paymentProcessed.getAccountNumber() +
                ", Amount=" + paymentProcessed.getAmount() +
                ", Currency=" + paymentProcessed.getCurrency() +
                ", PhoneNumber=" + paymentProcessed.getPhoneNumber().getPhoneNumber() +
                ", NumberOperationServer=" + paymentProcessed.getNumberOperationServer() +
                ", DateOperationServer=" + paymentProcessed.getDateOperationServer() +
                ", StatusPayment=" + paymentProcessed.getStatusPayment()
        );
        return paymentProcessed.getStatusPayment();
    }
}
