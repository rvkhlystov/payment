package ru.sbrf.payment.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Payments")
@NoArgsConstructor
@Getter
@Setter

public class PaymentEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Date dateOperationServer;
    private String statusPayment;
    //@ManyToOne
    //@JoinColumn(name = "client_id", nullable = false)
    private Long clientId;
    //@ManyToOne
    //@JoinColumn(name = "account_id", nullable = false)
    private Long accountId;
    private Long amount;
    private String currency;
    private String phoneNumber;
    private Date dateOperationApp;
    private Long numberOperationApp;
}
