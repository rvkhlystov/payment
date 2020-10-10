package ru.sbrf.payment.client.Check;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbrf.payment.client.AccountCredit;
import ru.sbrf.payment.client.AccountDebit;
import ru.sbrf.payment.client.Client;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.server.DataBaseClients;
import ru.sbrf.payment.server.Server;

import static org.junit.jupiter.api.Assertions.*;

class CheckClientTest {

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
}