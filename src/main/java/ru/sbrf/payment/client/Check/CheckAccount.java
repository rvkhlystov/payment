package ru.sbrf.payment.client.Check;

import lombok.Getter;
import ru.sbrf.payment.client.Account;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

import java.util.HashMap;

//добавить логирование

@Getter

//притянул за уши дженерики
public class CheckAccount {
    //private T account;

    public static <T> T checkAccount(Account account) {

        return (T) account;
    }

    public static void checkBalanceForMakeOperation(Account account, float amount) throws BusinessExceptions {
        if (account.getBalance() < amount) {
            throw new BusinessExceptions("Недостаточно средств на счете. ");
        }
    }

    public static void checkAccountNumber(HashMap<String, Account> accountsListOfClient, String accountNumberPayment) throws BusinessExceptions {
        if (accountsListOfClient.get(accountNumberPayment) == null) {
            throw new BusinessExceptions("Ошибка. Указанный счет не найден. ");
        }
    }
}
