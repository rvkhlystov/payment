//необходимо отказаться от передачи в сеттерах
// переменных clientNumber, accountNumber, phoneNumber, currency, amount
//Возможно через создание одноименных интерфейсов с атрибутами номер счета,описание счета и т.п.?

public class User implements Interaction {
    private String clientNumber;
    private String clientNumberDescription = "номер клиента";
    private long accountNumber;
    private String accountNumberDescription = "номер счета";
    private long phoneNumber;
    private String phoneNumberDescription = "номер телефона";
    private String currency;
    private String currencyDescription = "валюту";
    private float amount;
    private String amountDescription = "сумму";


    public void setClientNumber() {
        this.clientNumber = userInput(clientNumberDescription, clientNumber);
    }

    public void setAccountNumber() {
        this.accountNumber = userInput(accountNumberDescription, accountNumber);
    }

    public void setPhoneNumber() {
        this.phoneNumber = userInput(phoneNumberDescription, phoneNumber);
    }

    public void setCurrency() {
        this.currency = userInput(currencyDescription, currency);
    }

    public void setAmount() {
        this.amount = userInput(amountDescription, amount);
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public String getClientNumberDescription() {
        return clientNumberDescription;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getAccountNumberDescription() {
        return accountNumberDescription;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getPhoneNumberDescription() {
        return phoneNumberDescription;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCurrencyDescription() {
        return currencyDescription;
    }

    public float getAmount() {
        return amount;
    }

    public String getAmountDescription() {
        return amountDescription;
    }



}
