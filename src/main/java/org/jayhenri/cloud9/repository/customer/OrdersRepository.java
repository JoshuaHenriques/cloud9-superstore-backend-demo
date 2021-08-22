package org.jayhenri.cloud9.repository.customer;

import org.jayhenri.cloud9.model.customer.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * The interface Customer repository.
 */
@Repository
@Transactional
public interface OrdersRepository extends JpaRepository<Orders, UUID> {


}
