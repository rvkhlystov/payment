package ru.sbrf.payment.server.databases;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sbrf.payment.server.entity.AccountEntity;

@Repository
public interface AccountsCrudRepository extends CrudRepository<AccountEntity, Long> {
}
