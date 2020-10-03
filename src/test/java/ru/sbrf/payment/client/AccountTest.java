package ru.sbrf.payment.client;

import com.sun.tools.javac.util.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbrf.payment.common.Currency;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    public AccountTest() {

    }

    @Test
    void setBalance() {
        Account account1 = new Account(12345, Currency.RUB, 10000);
        account1.setBalance(5000);

        assertEquals(account1.getBalance(), 5000);
    }

    @Test
    void getAccountDescription() {
    }

    @Test
    void getAccountNumber() {
    }

    @Test
    void getAccountNumberDescription() {
    }

    @Test
    void getCurrency() {
    }

    @Test
    void getCurrencyDescription() {
    }

    @Test
    void getBalance() {
    }

    @Test
    void getBalanceDescription() {
    }
}