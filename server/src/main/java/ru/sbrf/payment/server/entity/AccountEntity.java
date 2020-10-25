package ru.sbrf.payment.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sbrf.payment.common.Currency;
import javax.persistence.*;


@Entity
@Table(name = "accounts")
@NoArgsConstructor
@Getter
@Setter

public class AccountEntity {
    @Id
    @GeneratedValue
    private Long id;

    //private Currency currency;
    @Column(name = "balance")
    private long balance;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client_id;
}




