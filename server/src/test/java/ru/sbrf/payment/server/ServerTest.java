package ru.sbrf.payment.server;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.Operations.Payment;
import ru.sbrf.payment.common.Operations.StatusPayment;
import ru.sbrf.payment.common.PhoneNumber.PhoneNumberRussian;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.server.Operations.PaymentProcessed;
import ru.sbrf.payment.server.client.AccountCredit;
import ru.sbrf.payment.server.client.AccountDebit;
import ru.sbrf.payment.server.client.Client;
import ru.sbrf.payment.server.databases.DataBaseClients;
import ru.sbrf.payment.server.databases.DataBasePayments;
import ru.sbrf.payment.server.service.Server;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    //проверка на корректность записи в базу платежа и корректность изменения баланса
    @Test
    void makePayment() throws BusinessExceptions {
        //Создаем сервер,базы данных и приложение
        Server server = new Server();
        DataBaseClients dataBaseClients = new DataBaseClients();
        DataBasePayments dataBasePayments = new DataBasePayments();

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000)));

        //создаем платеж
        Payment payment = new Payment(1, new Date(), "1", new PhoneNumberRussian("1234567890"), "12345", Currency.RUB, 100);

        //обрабатываем платеж на стороне сервера
        PaymentProcessed paymentProcessed = server.makePayment(payment, dataBaseClients, dataBasePayments);

        //Проверяем,что в базу записался верный платеж и баланс изменился корректно
        assertTrue(paymentProcessed.equals(dataBasePayments.getPayments().get(1))
                && dataBaseClients.getClients().get("1").getAccountsList().get("12345").getBalance() == 9900);
    }

    //проверка на корректность записи в базу платежа и корректность изменения баланса
    /*@Test
    void makePayment() throws BusinessExceptions {
        //Создаем сервер,базы данных и приложение
        Server server = new Server();
        DataBaseClients dataBaseClients = new DataBaseClients();
        DataBasePayments dataBasePayments = new DataBasePayments();
        ClientApplication clientApplication = new ClientApplication();

        //Эмулируем получение данных от пользователя
        Interaction userData = new Interaction("1", "12345", new PhoneNumberRussian("1234567890"), 100, Currency.RUB);

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000)));

        //создаем платеж на стороне приложения
        Payment payment = clientApplication.pay(userData);

        //обрабатываем платеж на стороне сервера
        PaymentProcessed paymentProcessed = server.makePayment(payment, dataBaseClients, dataBasePayments);

        //Проверяем,что в базу записался верный платеж и баланс изменился корректно
        assertTrue(paymentProcessed.equals(dataBasePayments.getPayments().get(1))
                && dataBaseClients.getClients().get("1").getAccountsList().get("12345").getBalance() == 9900);
    }*/

    //Проверяем,что дублирующие платежи не проходят и баланс меняется корректно
    @Test
    void makePaymentDouble() throws BusinessExceptions {
        //Создаем сервер,базы данных и приложение
        Server server = new Server();
        DataBaseClients dataBaseClients = new DataBaseClients();
        DataBasePayments dataBasePayments = new DataBasePayments();

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000)));

        //Создаем платеж
        Payment payment = new Payment(1, new Date(), "1", new PhoneNumberRussian("1234567890"), "12345", Currency.RUB, 100);

        //обрабатываем платеж на стороне сервера
        PaymentProcessed paymentProcessed = server.makePayment(payment, dataBaseClients, dataBasePayments);
        try {
            //Повторяем платеж
            payment = new Payment(1, new Date(), "1", new PhoneNumberRussian("1234567890"), "12345", Currency.RUB, 100);
            paymentProcessed = server.makePayment(payment, dataBaseClients, dataBasePayments);
        }
        catch (BusinessExceptions e) {
            //Проверяем, что баланс изменился корректно
            assertTrue(dataBaseClients.getClients().get("1").getAccountsList().get("12345").getBalance() == 9900);
        }

    }

    //Проверяем,что дублирующие платежи не проходят и баланс меняется корректно
/*    @Test
    void makePaymentDouble() throws BusinessExceptions {
        //Создаем сервер,базы данных и приложение
        Server server = new Server();
        DataBaseClients dataBaseClients = new DataBaseClients();
        DataBasePayments dataBasePayments = new DataBasePayments();
        ClientApplication clientApplication = new ClientApplication();

        //Эмулируем получение данных от пользователя
        Interaction userData = new Interaction("1", "12345", new PhoneNumberRussian("1234567890"), 100, Currency.RUB);

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000)));

        //Создаем платеж
        Payment payment = clientApplication.pay(userData);

        //обрабатываем платеж на стороне сервера
        PaymentProcessed paymentProcessed = server.makePayment(payment, dataBaseClients, dataBasePayments);
        try {
            //Повторяем платеж
            payment = clientApplication.pay(userData);
            paymentProcessed = server.makePayment(payment, dataBaseClients, dataBasePayments);
        }
        catch (BusinessExceptions e) {
            //Проверяем, что баланс изменился корректно
            assertTrue(dataBaseClients.getClients().get("1").getAccountsList().get("12345").getBalance() == 9900);
        }
    }*/



    //Проверка в случае отсутствия указанного клиента в базах
    @Test
    void makePaymentIncorrectClientNumber() throws BusinessExceptions {
        //Создаем сервер,базы данных и приложение
        Server server = new Server();
        DataBaseClients dataBaseClients = new DataBaseClients();
        DataBasePayments dataBasePayments = new DataBasePayments();

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000)));

        //создаем платеж на стороне приложения
        Payment payment = new Payment(1, new Date(), "3", new PhoneNumberRussian("1234567890"), "12345", Currency.RUB, 100);

        //обрабатываем платеж на стороне сервера
        PaymentProcessed paymentProcessed = server.makePayment(payment, dataBaseClients, dataBasePayments);

        //Убеждаемся, что в базу записался верный статус платежа
        assertTrue(dataBasePayments.getPayments().get(paymentProcessed.getNumberOperationServer()).getStatusPayment() == StatusPayment.DONTCLIENT);
    }

    //Проверка в случае отсутствия указанного клиента в базах
/*    @Test
    void makePaymentIncorrectClientNumber() throws BusinessExceptions {
        //Создаем сервер,базы данных и приложение
        Server server = new Server();
        DataBaseClients dataBaseClients = new DataBaseClients();
        DataBasePayments dataBasePayments = new DataBasePayments();
        ClientApplication clientApplication = new ClientApplication();

        //Эмулируем получение данных от пользователя
        Interaction userData = new Interaction("3", "12345", new PhoneNumberRussian("1234567890"), 100, Currency.RUB);

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000)));

        //создаем платеж на стороне приложения
        Payment payment = clientApplication.pay(userData);

        //обрабатываем платеж на стороне сервера
        PaymentProcessed paymentProcessed = server.makePayment(payment, dataBaseClients, dataBasePayments);

        //Убеждаемся, что в базу записался верный статус платежа
        assertTrue(dataBasePayments.getPayments().get(paymentProcessed.getNumberOperationServer()).getStatusPayment() == StatusPayment.DONTCLIENT);
    }*/


    //Проверяем как отрабатывает алгоритм в случае некорректного ввода номера счета
    @Test
    void makePaymentIncorrectAccountNumber() throws BusinessExceptions {
        //Создаем сервер,базы данных и приложение
        Server server = new Server();
        DataBaseClients dataBaseClients = new DataBaseClients();
        DataBasePayments dataBasePayments = new DataBasePayments();

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000)));

        //создаем платеж на стороне приложения
        Payment payment = new Payment(1, new Date(), "1", new PhoneNumberRussian("1234567890"), "123", Currency.RUB, 100);

        //обрабатываем платеж на стороне сервера
        PaymentProcessed paymentProcessed = server.makePayment(payment, dataBaseClients, dataBasePayments);

        //Убеждаемся, что в базу записался верный статус платежа
        assertTrue(dataBasePayments.getPayments().get(paymentProcessed.getNumberOperationServer()).getStatusPayment() == StatusPayment.DONTACCOUNT);

    }

/*    @Test
    void makePaymentIncorrectAccountNumber() throws BusinessExceptions {
        //Создаем сервер,базы данных и приложение
        Server server = new Server();
        DataBaseClients dataBaseClients = new DataBaseClients();
        DataBasePayments dataBasePayments = new DataBasePayments();
        ClientApplication clientApplication = new ClientApplication();

        //Эмулируем получение данных от пользователя
        Interaction userData = new Interaction("1", "123", new PhoneNumberRussian("1234567890"), 100, Currency.RUB);

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000)));

        //создаем платеж на стороне приложения
        Payment payment = clientApplication.pay(userData);

        //обрабатываем платеж на стороне сервера
        PaymentProcessed paymentProcessed = server.makePayment(payment, dataBaseClients, dataBasePayments);

        //Убеждаемся, что в базу записался верный статус платежа
        assertTrue(dataBasePayments.getPayments().get(paymentProcessed.getNumberOperationServer()).getStatusPayment() == StatusPayment.DONTACCOUNT);

    }*/

    //Проверяем как отрабатывает алгоритм в случае указания суммы перевода больше баланса счета
    @Test
    void makePaymentIncorrectAmount() throws BusinessExceptions {
        //Создаем сервер,базы данных и приложение
        Server server = new Server();
        DataBaseClients dataBaseClients = new DataBaseClients();
        DataBasePayments dataBasePayments = new DataBasePayments();

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000)));

        //создаем платеж
        Payment payment = new Payment(1, new Date(), "1", new PhoneNumberRussian("1234567890"), "12345", Currency.RUB, 100000);

        //обрабатываем платеж на стороне сервера
        PaymentProcessed paymentProcessed = server.makePayment(payment, dataBaseClients, dataBasePayments);

        //Убеждаемся, что в базу записался верный статус платежа
        assertTrue(dataBasePayments.getPayments().get(paymentProcessed.getNumberOperationServer()).getStatusPayment() == StatusPayment.DONTENOUGHAMOUNT);
    }

/*    @Test
    void makePaymentIncorrectAmount() throws BusinessExceptions {
        //Создаем сервер,базы данных и приложение
        Server server = new Server();
        DataBaseClients dataBaseClients = new DataBaseClients();
        DataBasePayments dataBasePayments = new DataBasePayments();
        ClientApplication clientApplication = new ClientApplication();

        //Эмулируем получение данных от пользователя
        Interaction userData = new Interaction("1", "12345", new PhoneNumberRussian("1234567890"), 100000, Currency.RUB);

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000)));

        //создаем платеж на стороне приложения
        Payment payment = clientApplication.pay(userData);

        //обрабатываем платеж на стороне сервера
        PaymentProcessed paymentProcessed = server.makePayment(payment, dataBaseClients, dataBasePayments);

        //Убеждаемся, что в базу записался верный статус платежа
        assertTrue(dataBasePayments.getPayments().get(paymentProcessed.getNumberOperationServer()).getStatusPayment() == StatusPayment.DONTENOUGHAMOUNT);
    }*/

    //Проверка алгоритма в случае указания номера счета другого клиента
    @Test
    void makePaymentAccountNumberOfOtherClient() throws BusinessExceptions {
        //Создаем сервер,базы данных и приложение
        Server server = new Server();
        DataBaseClients dataBaseClients = new DataBaseClients();
        DataBasePayments dataBasePayments = new DataBasePayments();

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000)));

        //создаем платеж
        Payment payment = new Payment(1, new Date(), "2", new PhoneNumberRussian("1234567890"), "12345", Currency.RUB, 100);

        //обрабатываем платеж на стороне сервера
        PaymentProcessed paymentProcessed = server.makePayment(payment, dataBaseClients, dataBasePayments);

        //Убеждаемся, что в базу записался верный статус платежа
        assertTrue(dataBasePayments.getPayments().get(paymentProcessed.getNumberOperationServer()).getStatusPayment() == StatusPayment.DONTACCOUNT);
    }

/*    @Test
    void makePaymentAccountNumberOfOtherClient() throws BusinessExceptions {
        //Создаем сервер,базы данных и приложение
        Server server = new Server();
        DataBaseClients dataBaseClients = new DataBaseClients();
        DataBasePayments dataBasePayments = new DataBasePayments();
        ClientApplication clientApplication = new ClientApplication();

        //Эмулируем получение данных от пользователя
        Interaction userData = new Interaction("2", "12345", new PhoneNumberRussian("1234567890"), 100, Currency.RUB);

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000)));

        //создаем платеж на стороне приложения
        Payment payment = clientApplication.pay(userData);

        //обрабатываем платеж на стороне сервера
        PaymentProcessed paymentProcessed = server.makePayment(payment, dataBaseClients, dataBasePayments);

        //Убеждаемся, что в базу записался верный статус платежа
        assertTrue(dataBasePayments.getPayments().get(paymentProcessed.getNumberOperationServer()).getStatusPayment() == StatusPayment.DONTACCOUNT);
    }*/

}