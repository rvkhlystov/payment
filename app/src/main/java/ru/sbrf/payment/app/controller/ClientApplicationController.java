package ru.sbrf.payment.app.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.sbrf.payment.app.service.ClientApplicationService;
import ru.sbrf.payment.app.service.Interaction;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.Operations.CreatorTransferPayment;
import ru.sbrf.payment.common.Operations.Payment;
import ru.sbrf.payment.common.Operations.StatusPayment;
import ru.sbrf.payment.common.PhoneNumber.PhoneNumberRussian;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import java.util.HashMap;

@RestController
@AllArgsConstructor
@Log

public class ClientApplicationController {
    private ClientApplicationService clientApplicationService;

    @GetMapping("/app")
    public String getName() {
        log.info("Request from outside=/app, Response=It's application");
        return "It's application";
    }

    //решить,как принимать значение валюты
    //@GetMapping("/app/clients/{clientNumber}/accounts/{accountNumber}/phonenumbers/{phoneNumber}/amount/{amount}/currency/{currency}")
    @GetMapping("/app/clients/{clientNumber}/accounts/{accountNumber}/phonenumbers/{phoneNumber}/amount/{amount}")
    public String pay(
            @PathVariable("clientNumber") String clientNumber,
            @PathVariable("accountNumber") String accountNumber,
            @PathVariable("phoneNumber") String phoneNumber,
            @PathVariable("amount") long amount
            //@PathVariable("currency") Currency currency
    ) throws BusinessExceptions {
        log.info("Request from outside " +
                "/app/clients/" + clientNumber +
                "/accounts/" + accountNumber +
                "/phonenumbers/" + phoneNumber +
                "/amount/" + amount
        );

        Payment payment = clientApplicationService.pay(new Interaction(
                clientNumber,
                accountNumber,
                new PhoneNumberRussian(phoneNumber),
                amount,
                Currency.RUB));
        log.info("clientApplicationService.pay " +
                "NumberOperationApp=" + payment.getNumberOperationApp() +
                ", DateOperationApp=" + payment.getDateOperationApp() +
                ", ClientNumber=" + payment.getClientNumber() +
                ", AccountNumber=" + payment.getAccountNumber() +
                ", Amount=" + payment.getAmount() +
                ", Currency=" + payment.getCurrency() +
                ", PhoneNumber=" +payment.getPhoneNumber().getPhoneNumber()
        );

        RestTemplate restTemplate = new RestTemplate();
        //HttpEntity<Payment> request = new HttpEntity<>(payment);

        //обходная реализация передачи платежа
        HashMap paymentTemp = CreatorTransferPayment.createTransferPayment(payment);
        HttpEntity<HashMap> request = new HttpEntity<>(paymentTemp);
        //конец обходной реализации
        log.info("Request for server " + request.toString());

        ResponseEntity<StatusPayment> responseEntity = restTemplate.postForEntity("http://127.0.0.1:8080/server/operations", request, StatusPayment.class);
        log.info("Response from server " + responseEntity.getBody());
        return responseEntity.getBody().name();
    }

}
