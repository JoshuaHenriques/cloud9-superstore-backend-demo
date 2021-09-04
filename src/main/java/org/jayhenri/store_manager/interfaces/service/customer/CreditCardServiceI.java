package org.jayhenri.store_manager.interfaces.service.customer;

import org.jayhenri.store_manager.model.customer.CreditCard;
import org.jayhenri.store_manager.model.customer.Customer;

import java.util.Set;
import java.util.UUID;

/**
 * The interface Credit card service i.
 */
public interface CreditCardServiceI {

    /**
     * Add.
     *
     * @param customer   the customer
     * @param creditCard the credit card
     */
    void add(Customer customer, CreditCard creditCard);

    /**
     * Remove.
     *
     * @param customer the customer
     * @param uuid     the uuid
     */
    void remove(Customer customer, UUID uuid);

    /**
     * Find all set.
     *
     * @param customer the customer
     * @return the set
     */
    Set<CreditCard> findAll(Customer customer);

    /**
     * Exists by id boolean.
     *
     * @param customer the customer
     * @param uuid     the uuid
     * @return the boolean
     */
    boolean existsById(Customer customer, UUID uuid);

    /**
     * Gets by id.
     *
     * @param customer the customer
     * @param uuid     the uuid
     * @return the by id
     */
    CreditCard getById(Customer customer, UUID uuid);

    /**
     * Exists by ccn boolean.
     *
     * @param ccn the ccn
     * @return the boolean
     */
    boolean existsByCCN(String ccn);
}
