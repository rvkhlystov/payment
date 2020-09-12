import ru.sbrf.payment.common.Interaction;
import ru.sbrf.payment.app.ClientApplication;
import ru.sbrf.payment.client.Account;
import ru.sbrf.payment.client.User;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.Payment;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.server.Server;

import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) throws BusinessExceptions {
        Server server = new Server();
        ClientApplication clientApplication = new ClientApplication();

        Account account1 = new Account(12345, Currency.RUB, 10000);
        User user1 = new User("1", account1, 79670401988);
        Account account2 = new Account(12346, Currency.RUB, 100000);
        User user2 = new User("2", account2, 79670001122);

        server.addClient(user1.getClientNumber(), user1);
        server.addClient(user2.getClientNumber(), user2);

        Interaction userData = new Interaction();

        Payment payment = clientApplication.pay(userData);
        server.makePayment(payment);



        try {
            userData.inputUserData();
            //user.saveUserData(userData.getClientNumber(), userData.getAccountNumber(), userData.getPhoneNumber(), userData.getCurrency(), userData.getAmount());
            ClientApplication.pay(userData);
        }
        catch (InputMismatchException e) {
            System.out.println("Ошибка. Номер счета, номер телефона и сумма могут содержать только цифры. Для осуществления перевода необходимо начать заново.");

        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }

}
