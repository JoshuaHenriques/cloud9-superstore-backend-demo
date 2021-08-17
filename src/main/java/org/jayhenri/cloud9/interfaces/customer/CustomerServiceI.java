package org.jayhenri.cloud9.interfaces.customer;

import org.jayhenri.cloud9.interfaces.ServiceI;

/**
 * The interface Customer service i.
 *
 * @param <T> the type parameter
 * @param <U> the type parameter
 */
public interface CustomerServiceI<T, U> extends ServiceI<T, U> {

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
    T getByEmail(String email);

    /**
     * Exists by ccn boolean.
     *
     * @param ccn the ccn
     * @return the boolean
     */
    boolean existsByCCN(String ccn);
}
