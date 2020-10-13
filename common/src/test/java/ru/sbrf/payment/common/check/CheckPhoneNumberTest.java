package ru.sbrf.payment.common.check;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CheckPhoneNumberTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkPhoneNumberTrue() throws BusinessExceptions {
        assertTrue(CheckPhoneNumber.checkPhoneNumber("9234567890") == "9234567890");
    }

    @Test
    void checkPhoneNumberError10Digits() {
        Throwable exception = assertThrows(BusinessExceptions.class, () -> CheckPhoneNumber.checkPhoneNumber("92345678901"));
        assertTrue(exception.getMessage() == "Ошибка. Номер телефона некорректный. Должно быть 10 цифр");
    }

    @Test
    void checkPhoneNumberErrorCharacter() {
        Throwable exception = assertThrows(BusinessExceptions.class, () -> CheckPhoneNumber.checkPhoneNumber("9A34567890"));
        assertTrue(exception.getMessage() == "Ошибка. Номер телефона некорректный. Должно быть 10 цифр");

    }

}