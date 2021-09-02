package org.jayhenri.store_manager.interfaces.service.customer;

import java.util.Set;
import java.util.UUID;

import org.jayhenri.store_manager.model.customer.Customer;
import org.jayhenri.store_manager.model.customer.Orders;

/**
 * The interface Order service i.
 */
public interface OrdersServiceI {

    /**
     * Add.
     *
     * @param customer the customer
     * @param orders   the orders
     */
    void add(Customer customer, Orders orders);

    /**
     * Update.
     *
     * @param orders the orders
     */
    void update(Orders orders);

    /**
     * Find all set.
     *
     * @param customer the customer
     * @return the set
     */
    Set<Orders> findAll(Customer customer);

    /**
     * Exists by id boolean.
     *
     * @param uuid the uuid
     * @return the boolean
     */
    boolean existsById(UUID uuid);

    /**
     * Gets by id.
     *
     * @param uuid the uuid
     * @return the by id
     */
    Orders getById(UUID uuid);
}
