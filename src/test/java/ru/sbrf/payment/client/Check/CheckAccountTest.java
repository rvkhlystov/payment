package ru.sbrf.payment.client.Check;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbrf.payment.client.Account;
import ru.sbrf.payment.client.AccountCredit;
import ru.sbrf.payment.client.AccountDebit;
import ru.sbrf.payment.client.Client;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

import static org.junit.jupiter.api.Assertions.*;

class CheckAccountTest {


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkAccountCredit() throws BusinessExceptions {
        AccountCredit accountCredit = new AccountCredit("2", Currency.RUB, 20000);

        try {
            assert CheckAccount.checkAccount(accountCredit) == accountCredit;
        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void checkAccountDebit() throws BusinessExceptions {
        AccountDebit accountDebit = new AccountDebit("1", Currency.RUB, 1000);

        try {
            assert CheckAccount.checkAccount(accountDebit) == accountDebit;
        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    /*@Test
    void checkAccount() throws BusinessExceptions {
        AccountDebit accountDebit = new AccountDebit("1", Currency.RUB, 1000);
        AccountCredit accountCredit = new AccountCredit("2", Currency.RUB, 20000);
        Account account = new Account("3", Currency.RUB, 30000);
        Client client = new Client("1", accountCredit);

        try {
            assert CheckAccount.checkAccount(account) == account;
        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }*/

    @Test
    void checkBalanceForMakeOperation() {
    }

    @Test
    void checkAccountNumber() {
    }
}