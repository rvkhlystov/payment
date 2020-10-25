package ru.sbrf.payment.server.databases;

import lombok.Getter;
import ru.sbrf.payment.server.client.Client;

import java.util.HashMap;

@Getter

public class DataBaseClients {

    private HashMap<String, Client> clients = new HashMap<>();

    public void addClient (Client client) {
        clients.put(client.getClientNumber(), client);
    }

}
