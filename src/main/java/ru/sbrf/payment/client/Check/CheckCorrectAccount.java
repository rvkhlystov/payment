package ru.sbrf.payment.client.Check;

import java.util.function.*;

public class CheckCorrectAccount<T extends Object> {

    public boolean checkAccount(Predicate<T> predicate, T account) {
        return predicate.test(account);
    }
}
