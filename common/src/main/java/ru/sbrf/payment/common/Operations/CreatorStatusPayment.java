package ru.sbrf.payment.common.Operations;

public class CreatorStatusPayment {
    public static StatusPayment createStatusPaymentFromString(String string) {
        switch (string) {
            case "Платеж инициирован": {
                return StatusPayment.PAYMENTINITIATED;
            }
            case "Указан неверный номер клиента": {
                return StatusPayment.DONTCLIENT;
            }
            case "Указан неверный номер счета": {
                return StatusPayment.DONTACCOUNT;
            }
            case "Недостаточно средств на счете": {
                return StatusPayment.DONTENOUGHAMOUNT;
            }
            case "Проверка пройдена успешно": {
                return StatusPayment.CHECKSUCCESSFULLY;
            }
            case "Оплата проведена": {
                return StatusPayment.PAYMENTCOMPLETED;
            }
            default: {
                return StatusPayment.DONTSTATUS;
            }
        }
    }
}
