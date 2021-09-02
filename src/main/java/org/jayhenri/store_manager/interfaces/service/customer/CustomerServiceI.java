package org.jayhenri.store_manager.interfaces.service.customer;

import org.jayhenri.store_manager.interfaces.service.ServiceI;
import org.jayhenri.store_manager.model.customer.Customer;

/**
 * The interface Customer service i.
 */
public interface CustomerServiceI extends ServiceI<Customer> {

    /**
     * Exists by email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    boolean existsByEmail(String email);

    /**
     * Exists by phone number boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    boolean existsByPhoneNumber(String phoneNumber);

    /**
     * Gets by email.
     *
     * @param email the email
     * @return the by email
     */
    Customer getByEmail(String email);

    /**
     * Exists by ccn boolean.
     *
     * @param ccn the ccn
     * @return the boolean
     */
    boolean existsByCCN(String ccn);
}
