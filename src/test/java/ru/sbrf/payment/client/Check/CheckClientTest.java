package ru.sbrf.payment.client.Check;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbrf.payment.app.ClientApplication;
import ru.sbrf.payment.client.AccountCredit;
import ru.sbrf.payment.client.AccountDebit;
import ru.sbrf.payment.client.Client;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.Interaction;
import ru.sbrf.payment.common.Operations.Payment;
import ru.sbrf.payment.common.Operations.PaymentProcessed;
import ru.sbrf.payment.common.PhoneNumber.PhoneNumberRussian;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.server.DataBaseClients;
import ru.sbrf.payment.server.DataBasePayments;
import ru.sbrf.payment.server.Server;

import static org.junit.jupiter.api.Assertions.*;

class CheckClientTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkCorrectClient() throws BusinessExceptions {
        //Создаем сервер,базы данных и приложение
        Server server = new Server();
        DataBaseClients dataBaseClients = new DataBaseClients();

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000)));

        try {

            CheckClient.checkClient("1", dataBaseClients.getClients());

        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void checkIncorrectClient() throws BusinessExceptions {
        //Создаем сервер,базы данных и приложение
        DataBaseClients dataBaseClients = new DataBaseClients();

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000)));

        try {
            CheckClient.checkClient("0", dataBaseClients.getClients());
        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }
}