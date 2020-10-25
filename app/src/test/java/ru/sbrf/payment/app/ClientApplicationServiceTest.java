package ru.sbrf.payment.app;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import ru.sbrf.payment.app.service.ClientApplicationService;
import ru.sbrf.payment.app.service.Interaction;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.Operations.Payment;
import ru.sbrf.payment.common.PhoneNumber.PhoneNumberRussian;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j

class ClientApplicationServiceTest {

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
        ClientApplicationService clientApplicationService = new ClientApplicationService();

        //Эмулируем получение данных от пользователя
        Interaction userData = new Interaction("1", "12345", new PhoneNumberRussian("1234567890"), 100, Currency.RUB);
        //Создаем платеж
        Payment payment1 = clientApplicationService.pay(userData);
        //Проверяем дублирование платежа
        Throwable exception = assertThrows(BusinessExceptions.class, () -> clientApplicationService.pay(userData));
        assertTrue(exception.getMessage() == "Попытка дублирующего платежа. Если уверены, что хотите повторить платеж, то попробуйте через 5 минут.");

    }

}