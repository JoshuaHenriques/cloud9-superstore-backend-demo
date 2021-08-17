package org.jayhenri.cloud9.interfaces;

/**
 * The interface Login service i.
 *
 * @param <T> the type parameter
 * @param <U> the type parameter
 */
public interface LoginServiceI<T, U> extends ServiceI<T, U> {

    /**
     * Exists by phone number boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    boolean existsByPhoneNumber(String phoneNumber);
}
