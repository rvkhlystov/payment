package ru.sbrf.payment.app;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.Interaction;
import ru.sbrf.payment.common.Operations.Payment;
import ru.sbrf.payment.common.PhoneNumber.PhoneNumberRussian;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j

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
        //Создаем платеж
        Payment payment1 = clientApplication.pay(userData);
        //Проверяем дублирование платежа
        Throwable exception = assertThrows(BusinessExceptions.class, () -> clientApplication.pay(userData));
        assertTrue(exception.getMessage() == "Попытка дублирующего платежа. Если уверены, что хотите повторить платеж, то попробуйте через 5 минут.");

    }

}