package ru.sbrf.payment.common.Operations;

public enum DescriptionStatusPayment {
    success("Платеж прошел успешно"),
    notEnoughFunds("Недостаточно средств на счете"),
    wrongPhoneNumber("Неверный номер телефона");

    DescriptionStatusPayment(String s) {
    }
}

