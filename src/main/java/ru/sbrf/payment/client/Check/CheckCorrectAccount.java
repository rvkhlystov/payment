package ru.sbrf.payment.client.Check;

import com.sun.corba.se.spi.orb.Operation;
import ru.sbrf.payment.client.AccountCredit;
import ru.sbrf.payment.client.AccountDebit;

import java.util.function.*;

public class CheckCorrectAccount<T extends Object> {

/*    public boolean checkAccount(Predicate<T> predicate, T account) {
        return predicate.test(account);
    }*/

    public static Predicate test() {
        return ((object) -> (!(object.getClass() == AccountDebit.class)) && (!(object.getClass() == AccountCredit.class)));
    }

}



