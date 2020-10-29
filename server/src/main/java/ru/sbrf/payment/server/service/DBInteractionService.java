package ru.sbrf.payment.server.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import ru.sbrf.payment.common.Operations.StatusPayment;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.common.exceptions.ClientNotFoundException;
import ru.sbrf.payment.server.Operations.PaymentProcessed;
import ru.sbrf.payment.server.databases.*;
import ru.sbrf.payment.server.entity.AccountEntity;
import ru.sbrf.payment.server.entity.ClientEntity;
import ru.sbrf.payment.server.entity.PaymentEntity;

import java.util.Optional;

@Service
@AllArgsConstructor
@Getter
@Log

public class DBInteractionService {
    private ClientsCrudRepository clientsCrudRepository;
    private AccountsCrudRepository accountsCrudRepository;
    private PaymentsCrudRepository paymentsCrudRepository;

    public Optional<ClientEntity> makeClientEntity(Long id) {
        return clientsCrudRepository.findById(id);
    }

    public Optional<AccountEntity> makeAccountEntity(Long id) {
        return accountsCrudRepository.findById(id);
    }

    public DataBaseClients createDataBaseClients(Optional <ClientEntity> clientEntityOptional) throws BusinessExceptions {
        DataBaseClients dataBaseClients = new DataBaseClients();
        if (clientEntityOptional.isPresent()) {
            dataBaseClients.addClient(Transformator.createClient(clientEntityOptional.get()));
        }
        return dataBaseClients;
    }

    public boolean addPaymentProcessedToDBPayments(PaymentProcessed paymentProcessed) {
        paymentsCrudRepository.save(Transformator.createPaymentEntity(paymentProcessed));
        return true;
    }

    public DataBasePayments createDBPayments() throws BusinessExceptions {
        DataBasePayments dataBasePayments = new DataBasePayments();
        Optional <PaymentEntity> paymentEntityOptional = paymentsCrudRepository.findById(paymentsCrudRepository.count());
        if (paymentEntityOptional.isPresent()) {
            dataBasePayments.addPayment(Transformator.createPaymentProcessed(paymentEntityOptional.get()));
        }
        return dataBasePayments;
    }

    public void changeBalance(PaymentProcessed paymentProcessed) {
        if (paymentProcessed.getStatusPayment() == StatusPayment.PAYMENTCOMPLETED) {
            AccountEntity accountEntity = accountsCrudRepository.findById(
                    new Long(paymentProcessed.getClientNumber()))
                    .orElseThrow(ClientNotFoundException::new);
            accountEntity.setBalance(accountEntity.getBalance() - paymentProcessed.getAmount());
            accountsCrudRepository.save(accountEntity);
        }

    }

}
