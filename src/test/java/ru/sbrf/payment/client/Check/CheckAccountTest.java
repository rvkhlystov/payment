package ru.sbrf.payment.client.Check;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbrf.payment.client.*;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.server.DataBaseClients;
import ru.sbrf.payment.server.Server;

class CheckAccountTest {


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkAccountCredit() {
        AccountCredit accountCredit = new AccountCredit("2", Currency.RUB, 20000);
        try {
            assert CheckAccount.checkAccount(accountCredit) == accountCredit;
        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void checkAccountDebit() {
        AccountDebit accountDebit = new AccountDebit("1", Currency.RUB, 1000);
        try {
            assert CheckAccount.checkAccount(accountDebit) == accountDebit;
        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void checkAccountDeposit() {
        AccountDeposit accountDeposit = new AccountDeposit("1", Currency.RUB, 1000);
        try {
            assert CheckAccount.checkAccount(accountDeposit) == accountDeposit;
        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void checkAccountCreditOld() {
        AccountCredit accountCredit = new AccountCredit("1", Currency.RUB, 1000);
        try {
            assert CheckAccount.checkAccountOld(accountCredit) == accountCredit;
        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void checkAccountDebitOld() {
        AccountDebit accountDebit = new AccountDebit("1", Currency.RUB, 1000);
        try {
            assert CheckAccount.checkAccountOld(accountDebit) == accountDebit;
        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void checkAccountDepositOld() {
        AccountDeposit accountDeposit = new AccountDeposit("1", Currency.RUB, 1000);
        try {
            assert CheckAccount.checkAccountOld(accountDeposit) == accountDeposit;
        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void checkBalanceForMakeOperation() {
    }

    @Test
    void checkCorrectAccountNumber() {
        //Создаем базу данных клиентов
        DataBaseClients dataBaseClients = new DataBaseClients();

        //добавляем в базу данных двух клиентов
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000)));

        try {
            CheckAccount.checkAccountNumber(dataBaseClients.getClients().get("1").getAccountsList(),"12345");
        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void checkIncorrectAccountNumber() {
        //Создаем базу данных клиентов
        DataBaseClients dataBaseClients = new DataBaseClients();

        //добавляем в базу данных двух клиентов
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000)));

        try {
            CheckAccount.checkAccountNumber(dataBaseClients.getClients().get("1").getAccountsList(),"123456");
        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }
}