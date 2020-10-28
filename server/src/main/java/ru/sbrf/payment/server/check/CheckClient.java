package ru.sbrf.payment.server.check;

import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.server.client.Client;
import ru.sbrf.payment.server.entity.ClientEntity;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Predicate;

public class CheckClient {

    public static void checkClient(String clientNumber, HashMap clients) throws BusinessExceptions {
        Optional<? extends Object> clientNumberOptional = Optional.ofNullable(clients.get(clientNumber));
        if (!clientNumberOptional.isPresent()) {
            throw new BusinessExceptions("Ошибка. Клиент не найден.");
        }
    }
}