import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Interaction userData = new Interaction();
        User user = new User();

        try {
            userData.inputUserData();
            user.saveUserData(userData.getClientNumber(), userData.getAccountNumber(), userData.getPhoneNumber(), userData.getCurrency(), userData.getAmount());
            PaymentApplication.pay(user);
        }
        catch (InputMismatchException e) {
            System.out.println("Ошибка. Номер счета, номер телефона и сумма могут содержать только цифры. Для осуществления перевода необходимо начать заново.");

        }
        catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }

}
