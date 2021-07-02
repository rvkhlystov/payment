package ru.sbrf.payment.server.databases;

import org.springframework.data.repository.CrudRepository;
import ru.sbrf.payment.server.entity.PaymentEntity;

public interface PaymentsCrudRepository extends CrudRepository<PaymentEntity, Long> {
}
