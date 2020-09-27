package ru.sbrf.payment.server;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbrf.payment.app.ClientApplication;
import ru.sbrf.payment.client.Account;
import ru.sbrf.payment.client.User;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.Interaction;
import ru.sbrf.payment.common.Payment;
import ru.sbrf.payment.common.PhoneNumber.PhoneNumberRussian;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void makePayment() throws BusinessExceptions {
        //Создаем сервер,базы данных и приложение
        Server server = new Server();
        DataBaseClients dataBaseClients = new DataBaseClients();
        DataBasePayments dataBasePayments = new DataBasePayments();
        ClientApplication clientApplication = new ClientApplication();

        //Эмулируем получение данных от пользователя
        Interaction userData = new Interaction("1", 12345, new PhoneNumberRussian("1234567890"), 100);

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient("1", new User("1", new Account(12345, Currency.RUB, 10000)));
        dataBaseClients.addClient("2", new User("2", new Account(12346, Currency.RUB, 100000)));

        try {
            //создаем платеж на стороне приложения
            Payment payment = clientApplication.pay(userData);

            //обрабатываем платеж на стороне сервера
            payment = server.makePayment(payment, dataBaseClients, dataBasePayments);

            //Проверяем,что в базу записался верный платеж
            assertTrue(payment.equals(dataBasePayments.getPayments().get(1)));

            //Проверяем, что баланс изменился корректно
            assertTrue(dataBaseClients.getClients().get("1").getAccount().getBalance() == 9900);


        }
        catch (InputMismatchException e) {
            System.out.println("Ошибка. Номер телефона и сумма могут содержать только цифры. Для осуществления перевода необходимо начать заново.");

        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }


    }


    @Test
    void makePaymentDouble() throws BusinessExceptions {
        //Создаем сервер,базы данных и приложение
        Server server = new Server();
        DataBaseClients dataBaseClients = new DataBaseClients();
        DataBasePayments dataBasePayments = new DataBasePayments();
        ClientApplication clientApplication = new ClientApplication();

        //Эмулируем получение данных от пользователя
        Interaction userData = new Interaction("1", 12345, new PhoneNumberRussian("1234567890"), 100);

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient("1", new User("1", new Account(12345, Currency.RUB, 10000)));
        dataBaseClients.addClient("2", new User("2", new Account(12346, Currency.RUB, 100000)));

        try {
            Payment payment = clientApplication.pay(userData);

            //обрабатываем платеж на стороне сервера
            payment = server.makePayment(payment, dataBaseClients, dataBasePayments);

            //Повторяем платеж
            payment = clientApplication.pay(userData);
            payment = server.makePayment(payment, dataBaseClients, dataBasePayments);

        }
        catch (InputMismatchException e) {
            System.out.println("Ошибка. Номер телефона и сумма могут содержать только цифры. Для осуществления перевода необходимо начать заново.");

        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
        //Проверяем, что баланс изменился корректно
        assertTrue(dataBaseClients.getClients().get("1").getAccount().getBalance() == 9900);
    }


    @Test
    void makePaymentIncorrectClientNumber() throws BusinessExceptions {
        //Создаем сервер,базы данных и приложение
        Server server = new Server();
        DataBaseClients dataBaseClients = new DataBaseClients();
        DataBasePayments dataBasePayments = new DataBasePayments();
        ClientApplication clientApplication = new ClientApplication();

        //Эмулируем получение данных от пользователя
        Interaction userData = new Interaction("3", 12345, new PhoneNumberRussian("1234567890"), 100);

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient("1", new User("1", new Account(12345, Currency.RUB, 10000)));
        dataBaseClients.addClient("2", new User("2", new Account(12346, Currency.RUB, 100000)));

        try {
            //создаем платеж на стороне приложения
            Payment payment = clientApplication.pay(userData);

            //обрабатываем платеж на стороне сервера
            payment = server.makePayment(payment, dataBaseClients, dataBasePayments);

        }
        catch (InputMismatchException e) {
            System.out.println("Ошибка. Номер телефона и сумма могут содержать только цифры. Для осуществления перевода необходимо начать заново.");

        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void makePaymentIncorrectAccountNumber() throws BusinessExceptions {
        //Создаем сервер,базы данных и приложение
        Server server = new Server();
        DataBaseClients dataBaseClients = new DataBaseClients();
        DataBasePayments dataBasePayments = new DataBasePayments();
        ClientApplication clientApplication = new ClientApplication();

        //Эмулируем получение данных от пользователя
        Interaction userData = new Interaction("1", 123, new PhoneNumberRussian("1234567890"), 100);

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient("1", new User("1", new Account(12345, Currency.RUB, 10000)));
        dataBaseClients.addClient("2", new User("2", new Account(12346, Currency.RUB, 100000)));

        try {
            //создаем платеж на стороне приложения
            Payment payment = clientApplication.pay(userData);

            //обрабатываем платеж на стороне сервера
            payment = server.makePayment(payment, dataBaseClients, dataBasePayments);
        }
        catch (InputMismatchException e) {
            System.out.println("Ошибка. Номер телефона и сумма могут содержать только цифры. Для осуществления перевода необходимо начать заново.");

        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void makePaymentIncorrectAmount() throws BusinessExceptions {
        //Создаем сервер,базы данных и приложение
        Server server = new Server();
        DataBaseClients dataBaseClients = new DataBaseClients();
        DataBasePayments dataBasePayments = new DataBasePayments();
        ClientApplication clientApplication = new ClientApplication();

        //Эмулируем получение данных от пользователя
        Interaction userData = new Interaction("1", 12345, new PhoneNumberRussian("1234567890"), 100000);

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient("1", new User("1", new Account(12345, Currency.RUB, 10000)));
        dataBaseClients.addClient("2", new User("2", new Account(12346, Currency.RUB, 100000)));

        try {
            //создаем платеж на стороне приложения
            Payment payment = clientApplication.pay(userData);

            //обрабатываем платеж на стороне сервера
            payment = server.makePayment(payment, dataBaseClients, dataBasePayments);

        }
        catch (InputMismatchException e) {
            System.out.println("Ошибка. Номер телефона и сумма могут содержать только цифры. Для осуществления перевода необходимо начать заново.");

        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void makePaymentAccountNumberOfOtherClient() throws BusinessExceptions {
        //Создаем сервер,базы данных и приложение
        Server server = new Server();
        DataBaseClients dataBaseClients = new DataBaseClients();
        DataBasePayments dataBasePayments = new DataBasePayments();
        ClientApplication clientApplication = new ClientApplication();

        //Эмулируем получение данных от пользователя
        Interaction userData = new Interaction("2", 12345, new PhoneNumberRussian("1234567890"), 100);

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient("1", new User("1", new Account(12345, Currency.RUB, 10000)));
        dataBaseClients.addClient("2", new User("2", new Account(12346, Currency.RUB, 100000)));

        try {
            //создаем платеж на стороне приложения
            Payment payment = clientApplication.pay(userData);

            //обрабатываем платеж на стороне сервера
            payment = server.makePayment(payment, dataBaseClients, dataBasePayments);

        }
        catch (InputMismatchException e) {
            System.out.println("Ошибка. Номер телефона и сумма могут содержать только цифры. Для осуществления перевода необходимо начать заново.");

        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }
}