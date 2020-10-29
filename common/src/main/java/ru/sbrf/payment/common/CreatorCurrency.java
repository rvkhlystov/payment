package ru.sbrf.payment.common;

public class CreatorCurrency {
    public static Currency createCurrencyFromString(String string) {
        switch (string) {
            default: {
                return Currency.RUB;
            }
        }
    }
}
