package ru.sbrf.payment.server.client;

import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.server.entity.ClientEntity;


//временный класс для создания класса клиента из данных БД
public class CreatorClientFromClientEntity  {
    public static Client createClient (ClientEntity clientEntity) throws BusinessExceptions {
        Client client = new Client(clientEntity.getId().toString(), CreatorAccountFromAccountEntity.createAccount(clientEntity.getAccounts().get(0)));
        client.delAccount(CreatorAccountFromAccountEntity.createAccount(clientEntity.getAccounts().get(0)).getAccountNumber());
        clientEntity.getAccounts().forEach(
                accountEntity -> {
                    try {
                        client.addAccount(CreatorAccountFromAccountEntity.createAccount(accountEntity));
                    } catch (BusinessExceptions exceptions) {
                        exceptions.printStackTrace();
                    }
                });
        return client;
    }
}
