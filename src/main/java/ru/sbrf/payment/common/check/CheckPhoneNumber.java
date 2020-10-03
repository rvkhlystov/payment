package ru.sbrf.payment.common.check;

import ru.sbrf.payment.common.exceptions.BusinessExceptions;

public class CheckPhoneNumber {

    public static long checkPhoneNumber(long phoneNumber) throws BusinessExceptions {
        int num = 0;
        String msg;
        while (phoneNumber != 0) {
            phoneNumber /= 10;
            num += 1;
        }
        if (num == 11) {
            return phoneNumber;
        }
        else {
            throw new BusinessExceptions("Ошибка. Номер телефона некорректный. Должно быть 11 цифр");
        }
    }

}
