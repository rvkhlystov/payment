package ru.sbrf.payment.client.Check;

import ru.sbrf.payment.client.AccountCredit;
import ru.sbrf.payment.client.AccountDebit;

import java.util.function.*;

public class CheckCorrectAccount {

/*    public boolean checkAccount(Predicate<T> predicate, T account) {
        return predicate.test(account);
    }*/

    public static Predicate test() {
        return ((account) -> (!(account.getClass() == AccountDebit.class)) && (!(account.getClass() == AccountCredit.class)));
    }

}



