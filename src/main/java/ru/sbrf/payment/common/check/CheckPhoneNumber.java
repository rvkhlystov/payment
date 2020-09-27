package ru.sbrf.payment.common.check;

import ru.sbrf.payment.common.exceptions.BusinessExceptions;

//реализовать логику проверки в зависимости от кода страны

public class CheckPhoneNumber {

    public static String checkPhoneNumber(String phoneNumber) throws BusinessExceptions {
        //int num = 0;
        //String msg;
        String numbers = "\\d+";

        if (phoneNumber.matches(numbers) && phoneNumber.length() == 10) {
            return phoneNumber;
        }

        /*long phoneNumberCheck = phoneNumber;
        while (phoneNumberCheck != 0) {
            phoneNumberCheck /= 10;
            num += 1;
        }
        if (num == 11) {
            return phoneNumber;
        }*/
        else {
            throw new BusinessExceptions("Ошибка. Номер телефона некорректный. Должно быть 10 цифр");
        }
    }

}
