//необходимо отказаться от передачи в сеттерах
// переменных clientNumber, accountNumber, phoneNumber, currency, amount
//Возможно через создание одноименных интерфейсов с атрибутами номер счета,описание счета и т.п.?

//реализовать проверку на корректность заполнения полей в конструкторе

public class User {
    private String clientNumber;
    private String clientNumberDescription = "номер клиента";
    private long accountNumber;
    private String accountNumberDescription = "номер счета";
    private long phoneNumber;
    private String phoneNumberDescription = "номер телефона";
    private String currency;
    private String currencyDescription = "валюта";
    private float amount;
    private String amountDescription = "сумма";

    public void saveUserData(String clientNumber, long accountNumber, long phoneNumber, String currency, float amount) throws BusinessExceptions {
        this.clientNumber = clientNumber;
        this.accountNumber = accountNumber;
        //this.phoneNumber.setPhoneNumber(phoneNumber.getPhoneNumber());
        PhoneNumber.checkPhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
        this.currency = currency;
        this.amount = amount;
    }
    public String getClientNumber() {
        return clientNumber;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public float getAmount() {
        return amount;
    }

    public String getClientNumberDescription() {
        return clientNumberDescription;
    }

    public String getAccountNumberDescription() {
        return accountNumberDescription;
    }

    public String getPhoneNumberDescription() {
        return phoneNumberDescription;
    }

    public String getCurrencyDescription() {
        return currencyDescription;
    }

    public String getAmountDescription() {
        return amountDescription;
    }
}
