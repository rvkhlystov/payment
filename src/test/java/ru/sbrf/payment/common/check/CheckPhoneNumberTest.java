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
        assertTrue(CheckPhoneNumber.checkPhoneNumber("9234567890") == "9234567890");
    }

    @Test
    void checkPhoneNumberError10Digits() throws BusinessExceptions {
        try {
            CheckPhoneNumber.checkPhoneNumber("92345678901");
        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void checkPhoneNumberErrorCharacter() throws BusinessExceptions {
        try {
            CheckPhoneNumber.checkPhoneNumber("9A34567890");
        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }

}