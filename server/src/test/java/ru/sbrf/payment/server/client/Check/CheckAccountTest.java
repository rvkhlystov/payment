package ru.sbrf.payment.server.client.Check;

import org.junit.jupiter.api.*;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.server.databases.DataBaseClients;
import ru.sbrf.payment.server.check.CheckAccount;
import ru.sbrf.payment.server.check.CheckCorrectAccount;
import ru.sbrf.payment.server.client.AccountCredit;
import ru.sbrf.payment.server.client.AccountDebit;
import ru.sbrf.payment.server.client.AccountDeposit;
import ru.sbrf.payment.server.client.Client;

import javax.validation.*;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CheckAccountTest {

    private static Validator validator;
    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkAccountIncorrect() throws BusinessExceptions {
        AccountCredit accountCredit = new AccountCredit("2", Currency.RUB, -1);
        Set<ConstraintViolation<AccountCredit>> constraintViolations = validator.validate(accountCredit);
        assertEquals(1, constraintViolations.size());
        assertEquals("must be greater than or equal to 0", constraintViolations.iterator().next().getMessage());
    }

    @Test
    void checkAccountDeposit() {
        AccountDeposit accountDeposit = new AccountDeposit("2", Currency.RUB, 20000L);
        Throwable exception = assertThrows(BusinessExceptions.class, () -> CheckAccount.checkAccount(CheckCorrectAccount.test(), accountDeposit));
        assertNotNull(exception.getMessage());
    }

    @Test
    void checkAccountCredit() throws BusinessExceptions {
        AccountCredit accountCredit = new AccountCredit("2", Currency.RUB, 20000L);
        assert CheckAccount.checkAccount(CheckCorrectAccount.test(), accountCredit) == accountCredit;
    }

    @Test
    void checkAccountDebit() throws BusinessExceptions {
        AccountDebit accountDebit = new AccountDebit("1", Currency.RUB, 1000L);
        assert CheckAccount.checkAccount(CheckCorrectAccount.test(), accountDebit) == accountDebit;
    }


    @Test
    void checkCorrectAccountNumber() {
        //Создаем базу данных клиентов
        DataBaseClients dataBaseClients = new DataBaseClients();

        //добавляем в базу данных двух клиентов
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000L)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000L)));

        //Проверка
        assertDoesNotThrow(() -> CheckAccount.checkAccountNumber(dataBaseClients.getClients().get("1").getAccountsList(),"12345"));
    }

    @Test
    void checkIncorrectAccountNumber() {
        //Создаем базу данных клиентов
        DataBaseClients dataBaseClients = new DataBaseClients();

        //добавляем в базу данных двух клиентов
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000L)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000L)));

        //проверка
        Throwable exception = assertThrows(BusinessExceptions.class, () -> CheckAccount.checkAccountNumber(dataBaseClients.getClients().get("1").getAccountsList(),"123456"));
        assertNotNull(exception.getMessage());
    }
}