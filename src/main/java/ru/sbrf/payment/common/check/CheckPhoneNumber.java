package ru.sbrf.payment.common.check;

import ru.sbrf.payment.common.exceptions.BusinessExceptions;

//реализовать логику проверки в зависимости от кода страны

public class CheckPhoneNumber {

    public static String checkPhoneNumber(String phoneNumber) throws BusinessExceptions {
        String numbers = "\\d+";

        //return phoneNumber;
        if (phoneNumber.matches(numbers) && phoneNumber.length() == 10) {
            return phoneNumber;
        }

        else {
            throw new BusinessExceptions("Ошибка. Номер телефона некорректный. Должно быть 10 цифр");
        }
    }
}
