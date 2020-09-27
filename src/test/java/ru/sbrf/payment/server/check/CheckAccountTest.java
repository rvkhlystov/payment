package ru.sbrf.payment.server.check;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbrf.payment.client.Account;
import ru.sbrf.payment.client.AccountDebit;
import ru.sbrf.payment.common.Currency;

import static org.junit.jupiter.api.Assertions.*;

class CheckAccountTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkBalanceForMakeOperation() {
    }

    @Test
    void checkAccountNumber() {
    }

    @Test
    void getAccount() {
        AccountDebit account = new AccountDebit(111, Currency.RUB, 1000);
        CheckAccount<AccountDebit> checkAccount = new CheckAccount<>(account);
        assertTrue(checkAccount.getAccount().getClass().getName() == account.getClass().getName());
    }
}