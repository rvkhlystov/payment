package ru.sbrf.payment.client.Check;

import lombok.Getter;
import ru.sbrf.payment.client.Account;
import ru.sbrf.payment.client.AccountCredit;
import ru.sbrf.payment.client.AccountDebit;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

import java.util.HashMap;
import java.util.function.*;

//добавить логирование исключений

@Getter

public class CheckAccount {

    //Проверка,что аккаунт является дебетовым или кредитным - используется функциональный интерфейс
    public static  <T extends Account> T checkAccount(T account) throws BusinessExceptions {
        Predicate<T> predicate = (object) -> (!(object.getClass() == AccountDebit.class)) && (!(object.getClass() == AccountCredit.class));
        if (new CheckCorrectAccount().checkAccount(predicate, account)) {
            throw new BusinessExceptions("Указан некорректный тип счета, должен быть кредитный или дебетовый");
        }
        else return account;
    }

    //Проверка,что аккаунт является дебетовым или кредитным - используется обобщение
    public static <T extends Account> T checkAccountOld(T account) throws BusinessExceptions {
        if ((!(account.getClass() == AccountDebit.class)) && (!(account.getClass() == AccountCredit.class))) {
            throw new BusinessExceptions("Указан некорректный тип счета, должен быть кредитный или дебетовый");
        }
        return account;
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
