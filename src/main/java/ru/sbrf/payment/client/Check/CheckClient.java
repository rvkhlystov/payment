package ru.sbrf.payment.client.Check;

import ru.sbrf.payment.client.Client;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

import java.util.HashMap;
import java.util.Optional;

public class CheckClient {
    public static void checkClient(String clientNumber, HashMap clients) throws BusinessExceptions {
        Optional<? extends Object> clientNumberOptional = Optional.ofNullable(clients.get(clientNumber));
        if (!clientNumberOptional.isPresent()) {
            throw new BusinessExceptions("Ошибка. Клиент не найден.");
        }

        //старая реализация
        /*if (clients.get(clientNumber) == null) {
            throw new BusinessExceptions("Ошибка. Клиент не найден.");
        }*/
    }



}
