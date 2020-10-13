package ru.sbrf.payment.client.Check;

import ru.sbrf.payment.common.exceptions.BusinessExceptions;

import java.util.HashMap;
import java.util.function.Supplier;


public class CheckClient {
    public static void checkClient(String clientNumber, HashMap clients) throws BusinessExceptions {
        if (clients.get(clientNumber) == null) {
            throw new BusinessExceptions("Ошибка. Клиент не найден.");
        }
    }



}
