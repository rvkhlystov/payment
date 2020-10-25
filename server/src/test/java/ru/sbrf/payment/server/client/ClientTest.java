package ru.sbrf.payment.server.client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.server.client.AccountCredit;
import ru.sbrf.payment.server.client.AccountDebit;
import ru.sbrf.payment.server.client.Client;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addAccount() throws BusinessExceptions {
        AccountCredit account = new AccountCredit("0", Currency.RUB, 999);
        Client client = new Client("123", account);
        client.addAccount(new AccountDebit("1", Currency.RUB, 100));
        client.addAccount(new AccountCredit("2", Currency.RUB, 1000));

        assertTrue(client.getAccountsList().get("0").equals(new AccountCredit("0", Currency.RUB, 999))
                && client.getAccountsList().get("1").equals(new AccountDebit("1", Currency.RUB, 100))
                && client.getAccountsList().get("2").equals(new AccountCredit("2", Currency.RUB, 1000)));
    }

    @Test
    void delAccount() {
    }

    void getBalance() throws BusinessExceptions {
        Client client = new Client("123", new AccountCredit("11", Currency.RUB, 999));
        client.addAccount(new AccountDebit("1", Currency.RUB, 100));
        client.addAccount(new AccountCredit("2", Currency.RUB, 1000));
        client.addAccount(new AccountDebit("3", Currency.RUB, 10000));

        assert client.getAccountsList().get("1").getBalance() == 100;
    }

    @Test
    void getClientNumber() {
    }

    @Test
    void getClientNumberDescription() {
    }

    @Test
    void getAccountsList() {
    }
}