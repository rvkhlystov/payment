package ru.sbrf.payment.app.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.sbrf.payment.common.Currency;
import ru.sbrf.payment.common.PhoneNumber.PhoneNumberRussian;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor


public class Interaction {

    @NotNull
    @Size(max = 10, min = 1)
    private String clientNumber;
    @NotNull
    private String accountNumber;
    @NotNull
    @Size(max = 10, min = 10)
    private PhoneNumberRussian phoneNumber;
    @Min(0)
    private long amount;
    private Currency currency;

}
