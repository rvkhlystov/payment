package ru.sbrf.payment.server.databases;

import ru.sbrf.payment.common.CreatorCurrency;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.Operations.CreatorStatusPayment;
import ru.sbrf.payment.common.PhoneNumber.PhoneNumberRussian;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.server.Operations.PaymentProcessed;
import ru.sbrf.payment.server.client.*;
import ru.sbrf.payment.server.entity.AccountEntity;
import ru.sbrf.payment.server.entity.ClientEntity;
import ru.sbrf.payment.server.entity.PaymentEntity;

public class Transformator {
    public static Account createAccount(AccountEntity accountEntity) throws BusinessExceptions {
        Account account;
        switch (accountEntity.getAccountTypes()) {
            case "Credit":
                account = new AccountCredit(accountEntity.getId().toString(), Currency.RUB, accountEntity.getBalance());
                break;
            case "Debit":
                account = new AccountDebit(accountEntity.getId().toString(), Currency.RUB, accountEntity.getBalance());
                break;
            case "Deposit":
                account = new AccountDeposit(accountEntity.getId().toString(), Currency.RUB, accountEntity.getBalance());
                break;
            default:
                throw new BusinessExceptions("Unexpected value: " + accountEntity.getAccountTypes());
        }
        return account;
    }

    public static Client createClient (ClientEntity clientEntity) throws BusinessExceptions {
        Client client = new Client(clientEntity.getId().toString(), Transformator.createAccount(clientEntity.getAccounts().get(0)));
        client.delAccount(Transformator.createAccount(clientEntity.getAccounts().get(0)).getAccountNumber());
        clientEntity.getAccounts().forEach(
                accountEntity -> {
                    try {
                        client.addAccount(Transformator.createAccount(accountEntity));
                    } catch (BusinessExceptions exceptions) {
                        exceptions.printStackTrace();
                    }
                });
        return client;
    }

    public static PaymentEntity createPaymentEntity (PaymentProcessed paymentProcessed) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setId(new Long(paymentProcessed.getNumberOperationServer()));
        paymentEntity.setDateOperationServer(paymentProcessed.getDateOperationServer());
        paymentEntity.setStatusPayment(paymentProcessed.getStatusPayment().name());
        paymentEntity.setClientId(new Long(paymentProcessed.getClientNumber()));
        paymentEntity.setAccountId(new Long(paymentProcessed.getAccountNumber()));
        paymentEntity.setAmount(paymentProcessed.getAmount());
        paymentEntity.setCurrency(paymentProcessed.getCurrency().toString());
        paymentEntity.setPhoneNumber(paymentProcessed.getPhoneNumber().getPhoneNumber());
        paymentEntity.setDateOperationApp(paymentProcessed.getDateOperationApp());
        paymentEntity.setNumberOperationApp(new Long(paymentProcessed.getNumberOperationApp()));
        return paymentEntity;
    }
    public static PaymentProcessed createPaymentProcessed (PaymentEntity paymentEntity) throws BusinessExceptions {
        PaymentProcessed paymentProcessed = new PaymentProcessed(
                        paymentEntity.getNumberOperationApp().intValue(),
                        paymentEntity.getDateOperationApp(),
                        paymentEntity.getClientId().toString(),
                        new PhoneNumberRussian(paymentEntity.getPhoneNumber()),
                        paymentEntity.getAccountId().toString(),
                        CreatorCurrency.createCurrencyFromString(paymentEntity.getCurrency()),
                        paymentEntity.getAmount(),
                        CreatorStatusPayment.createStatusPaymentFromString(paymentEntity.getStatusPayment()),
                        paymentEntity.getId().intValue(),
                        paymentEntity.getDateOperationServer()
                );

        return paymentProcessed;
    }
}
