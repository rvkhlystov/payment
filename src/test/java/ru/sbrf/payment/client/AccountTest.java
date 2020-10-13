package ru.sbrf.payment.client;

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
        AccountCredit account1 = new AccountCredit("12345", Currency.RUB, 10000);
        account1.changeBalance(5000);

        assertEquals(account1.getBalance(), 5000);
    }
    
    @Test
    void createAccount() {
        Account account = new Account("12345", Currency.RUB, 10000);
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