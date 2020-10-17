package ru.sbrf.payment.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.sbrf.payment.app.service.ClientApplicationService;
import ru.sbrf.payment.app.service.Interaction;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.Operations.Payment;
import ru.sbrf.payment.common.Operations.StatusPayment;
import ru.sbrf.payment.common.PhoneNumber.PhoneNumberRussian;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

@RestController
@AllArgsConstructor

public class ClientApplicationController {
    private ClientApplicationService clientApplicationService;

    @GetMapping("/app")
    public String getName() {
        return "It's application";
    }

    //@GetMapping("/app/clients/{clientNumber}/accounts/{accountNumber}/phonenumbers/{phoneNumber}/amount/{amount}/currency/{currency}")
    @GetMapping("/app/clients/{clientNumber}/accounts/{accountNumber}/phonenumbers/{phoneNumber}/amount/{amount}")
    public String pay(
            @PathVariable("clientNumber") String clientNumber,
            @PathVariable("accountNumber") String accountNumber,
            @PathVariable("phoneNumber") String phoneNumber,
            @PathVariable("amount") long amount
            //@PathVariable("currency") Currency currency
    ) throws BusinessExceptions {
        //решить,как принимать значение валюты

        PhoneNumberRussian phoneNumberRussian = new PhoneNumberRussian(phoneNumber);
        Payment payment = clientApplicationService.pay(new Interaction(clientNumber, accountNumber, phoneNumberRussian, amount, Currency.RUB));

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Payment> request = new HttpEntity<>(payment);

        ResponseEntity<StatusPayment> responseEntity = restTemplate.postForEntity("http://127.0.0.1:8080/server/operations", request, StatusPayment.class);


        return responseEntity.getBody().name();
    }

}
