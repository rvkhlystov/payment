package ru.sbrf.payment.client.Check;

import org.junit.jupiter.api.*;
import ru.sbrf.payment.client.*;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.server.DataBaseClients;

import static org.junit.jupiter.api.Assertions.*;

class CheckAccountTest {


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void checkAccountDepositNew() {
        AccountDeposit accountDeposit = new AccountDeposit("2", Currency.RUB, 20000);
        Throwable exception = assertThrows(BusinessExceptions.class, () -> CheckAccount.checkAccount(CheckCorrectAccount.test(), accountDeposit));
        assertNotNull(exception.getMessage());
    }

    @Test
    void checkAccountCreditNew() throws BusinessExceptions {
        AccountCredit accountCredit = new AccountCredit("2", Currency.RUB, 20000);
        assert CheckAccount.checkAccount(CheckCorrectAccount.test(), accountCredit) == accountCredit;
    }

    @Test
    void checkAccountDebitNew() throws BusinessExceptions {
        AccountDebit accountDebit = new AccountDebit("1", Currency.RUB, 1000);
        assert CheckAccount.checkAccount(CheckCorrectAccount.test(), accountDebit) == accountDebit;
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

        //Проверка
        assertDoesNotThrow(() -> CheckAccount.checkAccountNumber(dataBaseClients.getClients().get("1").getAccountsList(),"12345"));
    }

    @Test
    void checkIncorrectAccountNumber() {
        //Создаем базу данных клиентов
        DataBaseClients dataBaseClients = new DataBaseClients();

        //добавляем в базу данных двух клиентов
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000)));

        //проверка
        Throwable exception = assertThrows(BusinessExceptions.class, () -> CheckAccount.checkAccountNumber(dataBaseClients.getClients().get("1").getAccountsList(),"123456"));
        assertNotNull(exception.getMessage());
    }
}