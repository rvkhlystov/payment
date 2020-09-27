package ru.sbrf.payment.client;

public enum AccountType {
    ACCOUNT("счет"),
    ACCOUNT_DEBIT("дебетовый счет"),
    ACCOUNT_CREDIT("кредитный счет"),
    ACCOUNT_CREDIT_LINE("счет с кредитной линией");

    AccountType(String s) {
    }
}
