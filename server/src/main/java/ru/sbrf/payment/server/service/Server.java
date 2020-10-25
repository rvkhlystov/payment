package ru.sbrf.payment.server.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.sbrf.payment.common.Operations.Payment;
import ru.sbrf.payment.common.Operations.StatusPayment;
import ru.sbrf.payment.common.check.CheckFieldsInClass;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.server.Operations.PaymentProcessed;
import ru.sbrf.payment.server.check.CheckPayment;
import ru.sbrf.payment.server.client.Account;
import ru.sbrf.payment.server.client.Client;
import ru.sbrf.payment.server.databases.AccountsCrudRepository;
import ru.sbrf.payment.server.databases.ClientsCrudRepository;
import ru.sbrf.payment.server.databases.DataBaseClients;
import ru.sbrf.payment.server.databases.DataBasePayments;
import ru.sbrf.payment.server.entity.AccountEntity;
import ru.sbrf.payment.server.entity.ClientEntity;

import java.util.*;

@Service
@Getter

public class Server implements ServerInterface {

    private ClientsCrudRepository clientsCrudRepository;
    private AccountsCrudRepository accountsCrudRepository;

    private String serverName = "server.payment.sbrf.ru"; //имя данного сервера
    private String ip = "127.0.0.1"; //IP-адрес данного сервера
    private int port = 443; //номер порта для взаимодействия с приложением
    private String protocol = "https"; //название протокола взаимодействия


    private int numberOperationServer = 0;
    private StatusPayment statusPayment;

    public String getAccountEntity() throws BusinessExceptions {
        AccountEntity accountEntity = accountsCrudRepository.findById(1L).orElseThrow(BusinessExceptions::new);
        return accountEntity.toString();
    }

    @Override
    public PaymentProcessed makePayment(Payment payment, DataBaseClients dataBaseClients, DataBasePayments dataBasePayments) throws BusinessExceptions {

        CheckFieldsInClass.validate(payment);

        numberOperationServer += 1;
        statusPayment = StatusPayment.PAYMENTINITIATED;

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
