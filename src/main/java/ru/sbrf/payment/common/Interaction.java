package ru.sbrf.payment.common;

import lombok.Getter;
import ru.sbrf.payment.common.PhoneNumber.PhoneNumber;
import ru.sbrf.payment.common.PhoneNumber.PhoneNumberRussian;
import ru.sbrf.payment.common.check.CheckPhoneNumber;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import java.util.Scanner;

@Getter

public class Interaction {

    private String clientNumber;
    private long accountNumber;
    private PhoneNumberRussian phoneNumber;
    private float amount;

    //удалить метод ввода с консоли?
    /*public void inputUserData() throws BusinessExceptions {
        Scanner scanner = new Scanner(System.in);

            System.out.println("Для осуществления перевода необходимо ввести данные");

            System.out.print("номер клиента: ");
            clientNumber = scanner.next();

            System.out.print("номер счета: ");
            accountNumber = scanner.nextLong();

            System.out.print("номер телефона: +7");
            phoneNumber = scanner.nextLine();
            //CheckPhoneNumber.checkPhoneNumber(phoneNumber);

            System.out.print("сумма: ");
            amount = scanner.nextFloat();

            scanner.close();

    }*/

    public Interaction() {
    }

    public Interaction(String clientNumber, long accountNumber, PhoneNumberRussian phoneNumber, float amount) {
        this.clientNumber = clientNumber;
        this.accountNumber = accountNumber;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
    }
}
