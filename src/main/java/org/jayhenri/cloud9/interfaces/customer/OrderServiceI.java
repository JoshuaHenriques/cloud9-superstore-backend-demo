package org.jayhenri.cloud9.interfaces.customer;

import java.util.Set;

/**
 * The interface Order service i.
 *
 * @param <T> the type parameter
 * @param <C> the type parameter
 * @param <U> the type parameter
 */
public interface OrderServiceI<T, C, U> {

    /**
     * Add.
     *
     * @param t the t
     * @param c the c
     */
    void add(T t, C c);

    /**
     * Update.
     *
     * @param c the c
     */
    void update(C c);

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
     * @param u the u
     * @return the boolean
     */
    boolean existsById(U u);

    /**
     * Gets by id.
     *
     * @param u the u
     * @return the by id
     */
    C getById(U u);
}
