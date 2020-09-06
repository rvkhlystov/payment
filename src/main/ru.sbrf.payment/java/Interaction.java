
import java.util.Scanner;

public class Interaction {

    private String clientNumber;
    private long accountNumber;
    private long phoneNumber;
    private String currency;
    private float amount;



    public void inputUserData() throws BusinessExceptions {
        Scanner scanner = new Scanner(System.in);

            System.out.println("Для осуществления перевода необходимо ввести данные");

            System.out.print("номер клиента: ");
            clientNumber = scanner.next();

            System.out.print("номер счета: ");
            accountNumber = scanner.nextLong();

            System.out.print("номер телефона: +");
            phoneNumber = scanner.nextLong();
            PhoneNumber.checkPhoneNumber(phoneNumber);

            System.out.print("валюта: ");
            currency = scanner.next();

            System.out.print("сумма: ");
            amount = scanner.nextFloat();

            scanner.close();

    }

    public String getClientNumber() {
        return clientNumber;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public float getAmount() {
        return amount;
    }
}
