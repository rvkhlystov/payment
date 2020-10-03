package ru.sbrf.payment.common.Operations;

public enum StatusPayment {
    PAYMENTINITIATED("Платеж инициирован"),
    DONTCLIENT("Указан неверный номер клиента"),
    DONTACCOUNT("Указан неверный номер счета"),
    DONTENOUGHAMOUNT("Недостаточно средств на счете"),
    CHECKSUCCESSFULLY("Проверка пройдена успешно"),
    PAYMENTCOMPLETED("Оплата проведена");

    StatusPayment(String s) {
    }
}
