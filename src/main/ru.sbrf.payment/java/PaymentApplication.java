
import sun.security.x509.IPAddressName;



public class PaymentApplication {
    private String host; //имя хоста,вероятно стоит изменить тип переменной
    private IPAddressName ip; //ip-адрес,возможно необходимо будет изменить тип переменной
    private int port; //номер порта
    private String protocol; //название протокола, возможно стоит изменить тип

    //добавить геттеры и сеттеры?

    public static void pay() {
        User user = new User();
        user.setClientNumber();
        user.setAccountNumber();
        user.setPhoneNumber();
        user.setCurrency();
        user.setAmount();

        System.out.println(user.paymentOutput(user.getPhoneNumber()));
    }
}
