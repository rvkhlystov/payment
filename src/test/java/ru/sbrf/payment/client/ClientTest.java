package ru.sbrf.payment.client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbrf.payment.common.Currency;

class ClientTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addAccount() {
        Account account = new Account("0", Currency.RUB, 999);
        Client client = new Client("123", account);
        //Client client = new Client("123", new Account("11", Currency.RUB, 999));
        client.addAccount(new Account("1", Currency.RUB, 100));
        client.addAccount(new AccountCredit("2", Currency.RUB, 1000));
        //client.addAccount(new AccountDebit("3", Currency.RUB, 10000));


        assert client.getAccountsList().get("0").equals(new Account("0", Currency.RUB, 999));
        assert client.getAccountsList().get("1").equals(new Account("1", Currency.RUB, 100));
        assert client.getAccountsList().get("2").equals(new Account("2", Currency.RUB, 1000));
        //assert client.getAccountsList().get("0").equals(account);

    }

    @Test
    void delAccount() {
    }

    void getBalance() {
        Client client = new Client("123", new Account("11", Currency.RUB, 999));
        client.addAccount(new Account("1", Currency.RUB, 100));
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