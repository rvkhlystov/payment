package ru.sbrf.payment.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.Interaction;
import ru.sbrf.payment.common.Operations.Payment;
import ru.sbrf.payment.common.PhoneNumber.PhoneNumberRussian;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

import java.util.InputMismatchException;

class ClientApplicationTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void connectToServer() {
    }

    //Проверяем,что дублирующие платежи не проходят
    @Test
    void makePaymentDouble() throws BusinessExceptions {
        //Создаем приложение
        ClientApplication clientApplication = new ClientApplication();

        //Эмулируем получение данных от пользователя
        Interaction userData = new Interaction("1", "12345", new PhoneNumberRussian("1234567890"), 100, Currency.RUB);

        try {
            //Создаем платеж
            Payment payment1 = clientApplication.pay(userData);

            //Повторяем платеж
            Payment payment2 = clientApplication.pay(userData);
        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }

    }

}