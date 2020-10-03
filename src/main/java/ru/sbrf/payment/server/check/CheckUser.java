package ru.sbrf.payment.server.check;

import ru.sbrf.payment.common.exceptions.BusinessExceptions;

import java.util.HashMap;

//добавить в методы фиксацию статуса платежа в базе, если проверка завалилась

public class CheckUser {
    public static boolean checkUser(String clientNumber, HashMap clients) throws BusinessExceptions {
        if (clients.get(clientNumber) != null) {
            return true;
        }
        else {
            throw new BusinessExceptions("Ошибка. Клиент " + clientNumber + " не найден.");
        }
    }

}
