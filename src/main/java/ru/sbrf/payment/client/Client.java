package ru.sbrf.payment.client;
//необходимо отказаться от передачи в сеттерах
// переменных clientNumber, accountNumber, phoneNumber, currency, amount
//Возможно через создание одноименных интерфейсов с атрибутами номер счета,описание счета и т.п.?

//реализовать проверку на корректность заполнения полей в конструкторе

import lombok.Getter;

import java.util.HashMap;

@Getter

public class Client {
    private String clientNumber;
    private String clientNumberDescription = "номер клиента";
    //private Account account;
    private HashMap<String, Account> accountsList = new HashMap<>();
    private HashMap<String, AccountCredit> accountCreditList = new HashMap<>();
    private HashMap<String, AccountDebit> accountDebitList = new HashMap<>();

    public Client(String clientNumber, Account account) {

        this.clientNumber = clientNumber;
        accountsList.put(account.getAccountNumber(), account);
    }

    public void addAccount(Account account) {
        accountsList.put(account.getAccountNumber(), account);
    }

    public void delAccount(long accountNumber) {
        accountsList.remove(accountNumber);
    }

    public Account getAccount(String numberAccount) {
        return accountsList.get(numberAccount);
    }
}