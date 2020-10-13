package ru.sbrf.payment.server.check;

import ru.sbrf.payment.server.client.Account;
import ru.sbrf.payment.common.Operations.Payment;
import ru.sbrf.payment.common.Operations.StatusPayment;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;
import ru.sbrf.payment.server.DataBaseClients;

public class CheckPayment {

    public static StatusPayment checkPayment(Payment payment, DataBaseClients dataBaseClients) throws BusinessExceptions {
        //проверяем,есть ли такой клиент в базе
        try {
            //как тут лучше поступать,как я сделал или лучше грузить в метод полностью payment и dataBaseClients,
            // а внутри метода уже обрабатывать?
            CheckClient.checkClient(payment.getClientNumber(), dataBaseClients.getClients());
        }
        catch (BusinessExceptions e) {
            //System.out.println(e.getMessage());
            return StatusPayment.DONTCLIENT;
        }

        //проверяем корректность указанного пользователем счета
        try {
            //Наличие счета
            CheckAccount.checkAccountNumber(dataBaseClients.getClients().get(payment.getClientNumber()).getAccountsList(), payment.getAccountNumber());
            //Счет дебетовый или кредитный
            //CheckAccount.checkAccount(dataBaseClients.getClients().get(payment.getClientNumber()).getAccountsList().get(payment.getAccountNumber()));
            //Изменена строка в связи с изменением логики взаимодействия классов, проверяющих счета
            CheckAccount.checkAccount(CheckCorrectAccount.test(), dataBaseClients.getClients().get(payment.getClientNumber()).getAccountsList().get(payment.getAccountNumber()));
        }
        catch (BusinessExceptions e) {
            //System.out.println(e.getMessage());
            return StatusPayment.DONTACCOUNT;
        }

        //проверяем достаточность средств на счете
        //получаем сумму оплаты из платежки
        long amount = payment.getAmount();
        //получаем счет для дальнейшей работы с ним
        Account account = dataBaseClients.getClients().get(payment.getClientNumber()).getAccountsList().get(payment.getAccountNumber());
        try {
            CheckAccount.checkBalanceForMakeOperation(account, amount);
        }
        catch (BusinessExceptions e) {
            //System.out.println(e.getMessage());
            return StatusPayment.DONTENOUGHAMOUNT;
        }

        return StatusPayment.CHECKSUCCESSFULLY;
    }
}
