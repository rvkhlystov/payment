package ru.sbrf.payment.server.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import ru.sbrf.payment.common.exceptions.ClientNotFoundException;
import ru.sbrf.payment.server.databases.AccountsCrudRepository;
import ru.sbrf.payment.server.databases.ClientsCrudRepository;
import ru.sbrf.payment.server.entity.AccountEntity;
import ru.sbrf.payment.server.entity.ClientEntity;

@Service
@AllArgsConstructor
@Getter
@Log

public class ServerSecond {
    private ClientsCrudRepository clientsCrudRepository;
    private AccountsCrudRepository accountsCrudRepository;

    public String makeClientEntity(Long id) {
        ClientEntity clientEntity = clientsCrudRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        return clientEntity.getAccounts().toString();
    }

    public Long makeAccountEntity(Long id) {
        AccountEntity accountEntity = accountsCrudRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        return accountEntity.getBalance();
    }

}
