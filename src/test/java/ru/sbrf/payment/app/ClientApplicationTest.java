package ru.sbrf.payment.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbrf.payment.app.check.CheckDoublePayment;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.Interaction;
import ru.sbrf.payment.common.Payment;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void pay() throws BusinessExceptions {
        ClientApplication clientApplication = new ClientApplication();
        Interaction userData = new Interaction("1",
                12345,
                1234567890,
                100);
        Payment paymentExpected1 = clientApplication.pay(userData);

        Payment paymentActual1 = new Payment(1,
                paymentExpected1.getDateOperationApp(),
                "1",
                1234567890,
                12345,
                Currency.RUB,
                100);

        assertTrue(paymentActual1.equals(paymentExpected1));
        }


    @Test
    void returnStatusPayment() {
    }
}