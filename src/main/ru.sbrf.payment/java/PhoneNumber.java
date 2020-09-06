public class PhoneNumber {

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
