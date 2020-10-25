package ru.sbrf.payment.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@Getter
@Setter

public class ClientEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "first_name")
    private String firstName;

    @OneToMany(mappedBy = "client_id")
    private Set<AccountEntity> accounts;
}


