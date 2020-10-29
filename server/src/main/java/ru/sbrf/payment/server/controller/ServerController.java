package ru.sbrf.payment.server.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import ru.sbrf.payment.common.Operations.CreatorTransferPayment;
import ru.sbrf.payment.common.Operations.Payment;
import ru.sbrf.payment.common.Operations.StatusPayment;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.server.databases.DataBaseClients;
import ru.sbrf.payment.server.databases.DataBasePayments;
import ru.sbrf.payment.server.Operations.PaymentProcessed;
import ru.sbrf.payment.server.service.DBInteractionService;
import ru.sbrf.payment.server.service.Server;

import java.text.ParseException;
import java.util.HashMap;

@RestController
@AllArgsConstructor
@Log

public class ServerController {
    private Server server;
    private DBInteractionService dbInteractionService;

    @GetMapping("/server")
    public String getServerInfo() {
        log.info("Request from outside=/server, Response=It's server");
        return "It's server";
    }

    /*@GetMapping("/server/client/{clientId}")
    public String getClientAccounts(
            @PathVariable Long clientId) {
        log.info("Request /server/client/" + clientId.toString());
        return serverSecond.makeClientEntity(clientId);
        //return server.getAccountEntity();
    }*/

    /*@GetMapping("/server/account/{accountId}")
    public Long getAccountType(
            @PathVariable Long accountId) {
        log.info("Request /server/client/" + accountId.toString());
        return serverSecond.makeAccountEntity(accountId);
        //return server.getAccountEntity();
    }*/

    @PostMapping("/server/operations")
    public StatusPayment makePayment (
            //@RequestBody Payment payment
            @RequestBody HashMap paymentTemp //обходная реализация
            ) throws BusinessExceptions, ParseException {
        log.info("Request /server/operations/" + paymentTemp.toString());


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

        //Извлекаем данные из БД
        DataBaseClients dataBaseClients = dbInteractionService.createDataBaseClients(dbInteractionService.makeClientEntity(new Long(payment.getClientNumber())));
        DataBasePayments dataBasePayments = dbInteractionService.createDBPayments();
        log.info("dbInteractionService.createDBPayments(): " + dataBasePayments.getPayments().toString());

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
        //меняем баланс в БД
        dbInteractionService.changeBalance(paymentProcessed);
        //записываем платеж в БД
        dbInteractionService.addPaymentProcessedToDBPayments(paymentProcessed);
        return paymentProcessed.getStatusPayment();
    }
}
