package ru.sbrf.payment.server;

import lombok.Getter;
import ru.sbrf.payment.client.User;

import java.util.HashMap;

@Getter

public class DataBaseClients {

    private HashMap<String, User> clients = new HashMap<>();

    public void addClient (String clientNumber, User client) {
        clients.put(clientNumber, client);
    }

}
