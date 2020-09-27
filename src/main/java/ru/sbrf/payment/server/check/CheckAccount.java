package ru.sbrf.payment.server.check;

import lombok.Getter;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

//добавить в методы фиксацию статуса платежа в базе, если проверка завалилась
//добавить логирование

@Getter

//притянул за уши дженерики
public class CheckAccount<T> {
    private T account;

    public CheckAccount(T account) {
        this.account = account;
    }

    public static boolean checkBalanceForMakeOperation(float balance, float amount) throws BusinessExceptions {
        if (balance >= amount) {
            return true;
        }
        else {
            throw new BusinessExceptions("Недостаточно средств на счете. Баланс: " + balance + ". Не хватает: " + (amount - balance));
        }
    }
    public static boolean checkAccountNumber(long accountNumberUser, long accountNumberPayment) throws BusinessExceptions {
        if (accountNumberUser == accountNumberPayment) {
            return true;
        }
        else {
            throw new BusinessExceptions("Ошибка. Указанный счет " + accountNumberPayment + " не найден.");
        }
    }
}
