package org.jayhenri.cloud9.interfaces.service.customer;

import org.jayhenri.cloud9.model.customer.Cart;
import org.jayhenri.cloud9.model.customer.Customer;
import org.jayhenri.cloud9.model.item.Item;

import java.util.UUID;

/**
 * The interface Cart service i.
 *
 * @param <T> the type parameter
 * @param <C> the type parameter
 * @param <S> the type parameter
 * @param <U> the type parameter
 */
public interface CartServiceI {

    /**
     * Add.
     *
     * @param customer the customer
     * @param item     the item
     */
    void add(Customer customer, Item item);

    /**
     * Remove.
     *
     * @param customer the customer
     * @param uuid     the uuid
     */
    void remove(Customer customer, UUID uuid);

    /**
     * Empty.
     *
     * @param customer the customer
     */
    void empty(Customer customer);

    /**
     * Get s.
     *
     * @param customer the customer
     * @return the s
     */
    Cart get(Customer customer);
}
