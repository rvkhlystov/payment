
import sun.security.x509.IPAddressName;



public class PaymentApplication {
    private String host; //имя хоста,вероятно стоит изменить тип переменной
    private IPAddressName ip; //ip-адрес,возможно необходимо будет изменить тип переменной
    private int port; //номер порта
    private String protocol; //название протокола, возможно стоит изменить тип

    public boolean connectToServer(){
        //реализовать логику подключения к серверу
        return true;
    }

    public static void pay(User user) {

            if (Server.madePayment(user) == true) {
                System.out.println("Переведено " + user.getAmount() + " " + user.getCurrency() + " со счета " + user.getAccountNumber() + " на номер телефона +" + user.getPhoneNumber());

            }
            else {
                System.out.println("Платеж не прошел");
            }

            //проверка полученных данных
            System.out.println("-------------------------------");
            System.out.println(user.getClientNumberDescription() + ": " + user.getClientNumber());
            System.out.println(user.getAccountNumberDescription() + ": " + user.getAccountNumber());
            System.out.println(user.getPhoneNumberDescription() + ": +" + user.getPhoneNumber());
            System.out.println(user.getCurrencyDescription() + ": " + user.getCurrency());
            System.out.println(user.getAmountDescription() + ": " + user.getAmount());




    }
}
