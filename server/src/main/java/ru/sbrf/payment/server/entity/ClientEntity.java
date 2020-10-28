package ru.sbrf.payment.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Clients")
@NoArgsConstructor
@Getter
@Setter
public class ClientEntity {

    @Id
    @GeneratedValue
    private Long Id;

    @OneToMany(mappedBy = "client_id")
    //private Set<AccountEntity> accounts;
    private List<AccountEntity> accounts;
}


