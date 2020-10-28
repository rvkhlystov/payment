package ru.sbrf.payment.server.client;

import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.server.entity.AccountEntity;

//изменить реализацию с валютой - нужен отдельный метод
public class CreatorAccountFromAccountEntity {
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
}
