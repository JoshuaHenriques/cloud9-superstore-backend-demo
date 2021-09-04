package org.jayhenri.store_manager.repository.customer;

import org.jayhenri.store_manager.model.customer.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * The interface Orders repository.
 */
@Repository
@Transactional
public interface OrdersRepository extends JpaRepository<Orders, UUID> {


}
