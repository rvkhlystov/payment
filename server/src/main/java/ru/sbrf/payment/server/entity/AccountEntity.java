package ru.sbrf.payment.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sbrf.payment.common.AccountTypes;
import ru.sbrf.payment.common.Currency;

import javax.persistence.*;

@Entity
@Table(name = "Accounts")
@NoArgsConstructor
@Getter
@Setter
public class AccountEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long balance;
    private String currency;
    private String accountTypes;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client_id;
}




