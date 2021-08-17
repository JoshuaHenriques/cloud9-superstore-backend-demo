package org.jayhenri.cloud9.interfaces.customer;

import java.util.Set;

/**
 * The interface Credit card service i.
 *
 * @param <T> the type parameter
 * @param <C> the type parameter
 * @param <U> the type parameter
 */
public interface CreditCardServiceI<T, C, U> {

    /**
     * Add.
     *
     * @param t the t
     * @param C the c
     */
    void add(T t, C C);

    /**
     * Remove.
     *
     * @param t the t
     * @param u the u
     */
    void remove(T t, U u);

    /**
     * Find all set.
     *
     * @param t the t
     * @return the set
     */
    Set<C> findAll(T t);

    /**
     * Exists by id boolean.
     *
     * @param t the t
     * @param u the u
     * @return the boolean
     */
    boolean existsById(T t, U u);

    /**
     * Gets by id.
     *
     * @param t the t
     * @param u the u
     * @return the by id
     */
    C getById(T t, U u);
}
