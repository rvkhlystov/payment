package ru.sbrf.payment.client;
//необходимо отказаться от передачи в сеттерах
// переменных clientNumber, accountNumber, phoneNumber, currency, amount
//Возможно через создание одноименных интерфейсов с атрибутами номер счета,описание счета и т.п.?

//реализовать проверку на корректность заполнения полей в конструкторе

import lombok.Getter;
import lombok.Setter;
import ru.sbrf.payment.common.check.CheckPhoneNumber;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

@Getter
//@Setter

public class User {
    private String clientNumber;
    private String clientNumberDescription = "номер клиента";
    private Account account;
    //private long phoneNumber;
    //private String phoneNumberDescription = "номер телефона";

    public User(String clientNumber, Account account) throws BusinessExceptions {
        this.clientNumber = clientNumber;
        this.account = account;
        //CheckPhoneNumber.checkPhoneNumber(phoneNumber);
        //this.phoneNumber = phoneNumber;
    }
}