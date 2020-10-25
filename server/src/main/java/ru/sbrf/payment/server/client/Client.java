package ru.sbrf.payment.server.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.sbrf.payment.common.check.CheckFieldsInClass;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;

@Getter
@AllArgsConstructor

public class Client {

    @NotNull
    @Size(max = 10, min = 1)
    private String clientNumber;
    private HashMap<String, Account> accountsList = new HashMap<>();

    public Client(String clientNumber, Account account) {
        this.clientNumber = clientNumber;
        accountsList.put(account.getAccountNumber(), account);
    }

    public void addAccount(Account account) throws BusinessExceptions {
        accountsList.put(account.getAccountNumber(), CheckFieldsInClass.validate(account));
    }

    public void delAccount(String accountNumber) {
        accountsList.remove(accountNumber);
    }

    public Account getAccount(String numberAccount) {
        return accountsList.get(numberAccount);
    }
}