package ru.sbrf.payment.server.check;

import ru.sbrf.payment.server.client.AccountCredit;
import ru.sbrf.payment.server.client.AccountDebit;

import java.util.function.*;

public class CheckCorrectAccount {

/*    public boolean checkAccount(Predicate<T> predicate, T account) {
        return predicate.test(account);
    }*/

    public static Predicate test() {
        return ((account) -> (!(account.getClass() == AccountDebit.class)) && (!(account.getClass() == AccountCredit.class)));
    }

}
