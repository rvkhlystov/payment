package ru.sbrf.payment.common.check;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbrf.payment.client.AccountCredit;
import ru.sbrf.payment.client.Client;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.Operations.Payment;
import ru.sbrf.payment.common.PhoneNumber.PhoneNumberRussian;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CheckFieldsInClassTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void validateAccountCreditCorrect() {
        AccountCredit accountCredit = new AccountCredit("12", Currency.RUB, 1000);
        assertDoesNotThrow(() -> CheckFieldsInClass.validate(accountCredit));
    }

    @Test
    void validateAccountCreditIncorrectAccountNumber() {
        AccountCredit accountCredit = new AccountCredit(null, Currency.RUB, 1000);
        Throwable exception = assertThrows(BusinessExceptions.class, () -> CheckFieldsInClass.validate(accountCredit));
        assertNotNull(exception.getMessage());
    }

    @Test
    void validateAccountCreditIncorrectBalance() {
        AccountCredit accountCredit = new AccountCredit("1234", Currency.RUB, -1000);
        Throwable exception = assertThrows(BusinessExceptions.class, () -> CheckFieldsInClass.validate(accountCredit));
        assertNotNull(exception.getMessage());
    }

    @Test
    void validateClientCorrect() {
        AccountCredit accountCredit = new AccountCredit(null, Currency.RUB, 1000);
        Client client = new Client("12345", accountCredit);
        assertDoesNotThrow(() -> CheckFieldsInClass.validate(client));
    }

    @Test
    void validateClientIncorrect() {
        AccountCredit accountCredit = new AccountCredit(null, Currency.RUB, 1000);
        Client client = new Client("12345678901", accountCredit);
        Throwable exception = assertThrows(BusinessExceptions.class, () -> CheckFieldsInClass.validate(client));
        assertNotNull(exception.getMessage());
    }

    @Test
    void validatePaymentCorrect() throws BusinessExceptions {
        PhoneNumberRussian phoneNumberRussian = new PhoneNumberRussian("9876543210");
        Payment payment = new Payment(1, new Date(), "12345", phoneNumberRussian, "123", Currency.RUB, 1000);
        assertDoesNotThrow(() -> CheckFieldsInClass.validate(payment));
    }

    @Test
    void validatePaymentIncorrect() throws BusinessExceptions {
        PhoneNumberRussian phoneNumberRussian = new PhoneNumberRussian("9876543210");
        Payment payment = new Payment(1, new Date(), "12345", phoneNumberRussian, "123", Currency.RUB, -1000);
        Throwable exception = assertThrows(BusinessExceptions.class, () -> CheckFieldsInClass.validate(payment));
        assertNotNull(exception.getMessage());
    }

}