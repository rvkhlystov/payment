package ru.sbrf.payment.client.Check;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbrf.payment.server.check.CheckClient;
import ru.sbrf.payment.server.client.AccountCredit;
import ru.sbrf.payment.server.client.AccountDebit;
import ru.sbrf.payment.server.client.Client;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.server.DataBaseClients;
import ru.sbrf.payment.server.Server;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CheckClientTest {


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
    void checkCorrectClient() {
        //Создаем сервер,базы данных и приложение
        Server server = new Server();
        DataBaseClients dataBaseClients = new DataBaseClients();

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000)));

        assertDoesNotThrow(() -> CheckClient.checkClient("1", dataBaseClients.getClients()));
    }

    @Test
    void checkIncorrectClient() {
        //Создаем сервер,базы данных и приложение
        DataBaseClients dataBaseClients = new DataBaseClients();

        //добавляем в базы данных двух клиентов
        dataBaseClients.addClient(new Client("1", new AccountDebit("12345", Currency.RUB, 10000)));
        dataBaseClients.addClient(new Client("2", new AccountCredit("12346", Currency.RUB, 100000)));

        Throwable exception = assertThrows(BusinessExceptions.class, () -> CheckClient.checkClient("0", dataBaseClients.getClients()));
        //assertNotNull(exception.getMessage());
        assertTrue(exception.getMessage() == "Ошибка. Клиент не найден.");
    }

    @Test
    void checkClientNumberEqualNull() {
        //Создаем сервер,базы данных и приложение
        DataBaseClients dataBaseClients = new DataBaseClients();

        //добавляем в базу данных клиента с пустым номером клиента
        Client client = new Client(null, new AccountDebit("12345", Currency.RUB, 10000));

        Set<ConstraintViolation<Client>> constraintViolations = validator.validate(client);
        assertEquals(1, constraintViolations.size());
        assertEquals("must not be null", constraintViolations.iterator().next().getMessage());
    }

    @Test
    void checkIncorrectClientNumber() {
        //Создаем сервер,базы данных и приложение
        DataBaseClients dataBaseClients = new DataBaseClients();

        //добавляем в базу данных клиента с пустым номером клиента
        Client client = new Client("12345678901", new AccountDebit("12345", Currency.RUB, 10000));

        Set<ConstraintViolation<Client>> constraintViolations = validator.validate(client);
        assertEquals(1, constraintViolations.size());
        assertEquals("size must be between 1 and 10", constraintViolations.iterator().next().getMessage());
    }

}