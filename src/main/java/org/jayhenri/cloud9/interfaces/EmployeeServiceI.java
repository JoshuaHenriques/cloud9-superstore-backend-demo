package org.jayhenri.cloud9.interfaces;

/**
 * The interface Employee service i.
 *
 * @param <T> the type parameter
 * @param <U> the type parameter
 */
public interface EmployeeServiceI<T, U> extends ServiceI<T, U> {

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
}
