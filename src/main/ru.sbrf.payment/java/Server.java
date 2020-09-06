
public class Server {

    private String serverName = "server.payment.sbrf.ru"; //имя данного сервера
    private String ip = "127.0.0.1"; //IP-адрес данного сервера
    private int port = 443; //номер порта для взаимодействия с приложением
    private String protocol = "https"; //название протокола взаимодействия


    public static boolean madePayment(User user) {
        //добавить логику проведения платежа
        Boolean operationResult = true;
        return operationResult;
    }

    public String getHost() {
        return serverName;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getProtocol() {
        return protocol;
    }
}
