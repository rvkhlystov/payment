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

    @Test
    void setBalance() {
        AccountCredit account1 = new AccountCredit("12345", Currency.RUB, 10000L);
        account1.changeBalance(5000L);

        assertEquals(account1.getBalance(), 5000L);
    }

/*    @Test
    void setBalanceNegative() {
        AccountCredit account1 = new AccountCredit("12345", Currency.RUB, -1L);
        //account1.changeBalance(5000);

        //assertEquals(account1.getBalance(), 5000);

    }*/
    

}