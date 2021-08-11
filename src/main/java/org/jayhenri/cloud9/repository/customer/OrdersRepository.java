package org.jayhenri.cloud9.repository.customer;

import org.jayhenri.cloud9.model.customer.Customer;
import org.jayhenri.cloud9.model.customer.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * The interface Customer repository.
 */
@Repository
public interface OrdersRepository extends JpaRepository<Orders, UUID> {


}
