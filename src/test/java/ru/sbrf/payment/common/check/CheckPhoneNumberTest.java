package ru.sbrf.payment.common.check;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbrf.payment.app.check.CheckDoublePayment;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

import static org.junit.jupiter.api.Assertions.*;

class CheckPhoneNumberTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkPhoneNumberTrue() throws BusinessExceptions {
        assertTrue(CheckPhoneNumber.checkPhoneNumber(79234567890L) == 79234567890L);
    }

    @Test
    void checkPhoneNumberError11Digits() throws BusinessExceptions {
        try {
            CheckPhoneNumber.checkPhoneNumber(792345678901L);
        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }


}