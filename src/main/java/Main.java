import ru.sbrf.payment.client.AccountCredit;
import ru.sbrf.payment.common.Interaction;
import ru.sbrf.payment.app.ClientApplication;
import ru.sbrf.payment.client.Account;
import ru.sbrf.payment.client.User;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.Payment;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.server.DataBaseClients;
import ru.sbrf.payment.server.DataBasePayments;
import ru.sbrf.payment.server.Server;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.function.Consumer;

//Реализовать ко всем классам тесты

public class Main {
    public static void main(String[] args) throws BusinessExceptions {

        /*Server server = new Server();
        DataBaseClients dataBaseClients = new DataBaseClients();
        DataBasePayments dataBasePayments = new DataBasePayments();
        ClientApplication clientApplication = new ClientApplication();
        Interaction userData = new Interaction();

        User user1 = new User("1", new Account(12345, Currency.RUB, 10000));
        User user2 = new User("2", new Account(12346, Currency.RUB, 100000));

        dataBaseClients.addClient(user1.getClientNumber(), user1);
        dataBaseClients.addClient(user2.getClientNumber(), user2);

        try {
            userData.inputUserData();
            Payment payment = clientApplication.pay(userData);
            payment = server.makePayment(payment, dataBaseClients, dataBasePayments);
            clientApplication.returnStatusPayment(payment);

            //Реализовать тесты
            System.out.println();
            System.out.println("Повторяем платеж");
            payment = clientApplication.pay(userData);
            payment = server.makePayment(payment, dataBaseClients, dataBasePayments);
            clientApplication.returnStatusPayment(payment);
        }
        catch (InputMismatchException e) {
            System.out.println("Ошибка. Номер телефона и сумма могут содержать только цифры. Для осуществления перевода необходимо начать заново.");

        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }

        //Реализовать тесты
        System.out.println("Баланс после проведения платежа: " + dataBaseClients.getClients().get(userData.getClientNumber()).getAccount().getBalance());
*/



    }

}
