package ru.sbrf.payment.common.Operations;

import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.PhoneNumber.PhoneNumberRussian;
import ru.sbrf.payment.common.exceptions.BusinessExceptions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class CreatorTransferPayment {
    public static HashMap createTransferPayment (Payment payment) {
        HashMap paymentTemp = new HashMap();
        paymentTemp.put("numberOperationApp", payment.getNumberOperationApp());
        paymentTemp.put("dateOperationApp", payment.getDateOperationApp().toString());
        paymentTemp.put("clientNumber", payment.getClientNumber());
        paymentTemp.put("phoneNumber", payment.getPhoneNumber().getPhoneNumber());
        paymentTemp.put("accountNumber", payment.getAccountNumber());
        paymentTemp.put("currency", payment.getCurrency());
        paymentTemp.put("amount", payment.getAmount());
        return paymentTemp;
    }
    public static Payment createPaymentFromTransferPayment(HashMap paymentTemp) throws ParseException, BusinessExceptions {
        //приводим к правильным типам
        DateFormat format = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
        Date dateOperationApp = format.parse((String) paymentTemp.get("dateOperationApp"));
        PhoneNumberRussian phoneNumber = new PhoneNumberRussian((String) paymentTemp.get("phoneNumber"));
        String stringToConvert = String.valueOf(paymentTemp.get("amount"));
        Long convertedLong = Long.parseLong(stringToConvert);
        long amount = convertedLong;

        //формируем платеж из полученных данных
        Payment payment = new Payment(
                (int) paymentTemp.get("numberOperationApp"),
                dateOperationApp,
                (String) paymentTemp.get("clientNumber"),
                phoneNumber,
                (String) paymentTemp.get("accountNumber"),
                //необходимо получать от пользователя
                Currency.RUB,
                amount
        );
        return payment;
    }

}
