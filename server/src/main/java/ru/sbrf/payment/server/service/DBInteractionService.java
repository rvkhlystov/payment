package ru.sbrf.payment.server.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.common.exceptions.ClientNotFoundException;
import ru.sbrf.payment.server.client.CreatorClientFromClientEntity;
import ru.sbrf.payment.server.databases.AccountsCrudRepository;
import ru.sbrf.payment.server.databases.ClientsCrudRepository;
import ru.sbrf.payment.server.databases.DataBaseClients;
import ru.sbrf.payment.server.entity.AccountEntity;
import ru.sbrf.payment.server.entity.ClientEntity;

import javax.swing.text.html.parser.Entity;
import java.util.Optional;

@Service
@AllArgsConstructor
@Getter
@Log

public class DBInteractionService {
    private ClientsCrudRepository clientsCrudRepository;
    private AccountsCrudRepository accountsCrudRepository;

    public Optional<ClientEntity> makeClientEntity(Long id) {
        return clientsCrudRepository.findById(id);
    }

    public Optional<AccountEntity> makeAccountEntity(Long id) {
        return accountsCrudRepository.findById(id);
    }

    public DataBaseClients createDataBaseClients(Optional <ClientEntity> clientEntityOptional) throws BusinessExceptions {
        DataBaseClients dataBaseClients = new DataBaseClients();
        if (clientEntityOptional.isPresent()) {
            dataBaseClients.addClient(CreatorClientFromClientEntity.createClient(clientEntityOptional.get()));
        }
        return dataBaseClients;
    }

    public Long changeBalance(Long id ,Long amount) {
        AccountEntity accountEntity = accountsCrudRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        accountEntity.setBalance(accountEntity.getBalance() - amount);
        accountsCrudRepository.save(accountEntity);
        return accountEntity.getBalance();
    }

}
