package ru.sbrf.payment.server.databases;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sbrf.payment.server.entity.ClientEntity;

@Repository
public interface ClientsCrudRepository extends CrudRepository<ClientEntity, Long> {
}
