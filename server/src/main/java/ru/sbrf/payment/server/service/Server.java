package ru.sbrf.payment.server.service;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import ru.sbrf.payment.common.Operations.Payment;
import ru.sbrf.payment.common.Operations.StatusPayment;
import ru.sbrf.payment.common.check.CheckFieldsInClass;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.server.Operations.PaymentProcessed;
import ru.sbrf.payment.server.check.CheckPayment;
import ru.sbrf.payment.server.databases.DataBaseClients;
import ru.sbrf.payment.server.databases.DataBasePayments;

import java.util.*;

@Service
//@AllArgsConstructor
@Getter
@Log

public class Server implements ServerInterface {

    //private ClientsCrudRepository clientsCrudRepository;
    //private AccountsCrudRepository accountsCrudRepository;
    //private int numberOperationServer = 0;
    //private StatusPayment statusPayment;


    /*public String getAccountEntity() {
        //AccountEntity accountEntity = accountsCrudRepository.findById(1L).orElseThrow(ClientNotFoundException::new);
        //String accountEntity = accountsCrudRepository.findById(1L).toString();
        Iterable<AccountEntity> accountEntityIterable = accountsCrudRepository.findAll();
        List<AccountEntity> accountEntityList = new ArrayList<>();
        accountEntityIterable.forEach(accountEntity -> accountEntityList.add(accountEntity));
        System.out.println(accountEntityList);
        return String.valueOf(accountEntityList);
    }*/

    /*public String makeClientEntity(Long id) {
        ClientEntity clientEntity = clientsCrudRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        return clientEntity.getId().toString();
    }*/

    @Override
    public PaymentProcessed makePayment(Payment payment, DataBaseClients dataBaseClients, DataBasePayments dataBasePayments) throws BusinessExceptions {

        CheckFieldsInClass.validate(payment);

        //переписать, необходимо будет извлекать последний номер из БД платежей
        int numberOperationServer = 1;
        StatusPayment statusPayment = StatusPayment.PAYMENTINITIATED;

        //Проверяем корректность платежа
        statusPayment = CheckPayment.checkPayment(payment, dataBaseClients);

        //меняем баланс
        if (statusPayment == StatusPayment.CHECKSUCCESSFULLY) {
            dataBaseClients.getClients().get(payment.getClientNumber()).getAccountsList().get(payment.getAccountNumber()).changeBalance(payment.getAmount());
            statusPayment = StatusPayment.PAYMENTCOMPLETED;
        }
        PaymentProcessed paymentProcessed = new PaymentProcessed(payment, statusPayment, numberOperationServer, new Date());

        //Добавляем платеж в базу
        dataBasePayments.addPayment(paymentProcessed);
        return paymentProcessed;
    }

}
