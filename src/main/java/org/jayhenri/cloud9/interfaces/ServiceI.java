package org.jayhenri.cloud9.interfaces;

import java.util.List;

/**
 * The interface Service i.
 *
 * @param <T> the type parameter
 * @param <U> the type parameter
 */
public interface ServiceI<T, U> {

    /**
     * Add.
     *
     * @param t the t
     */
    void add(T t);

    /**
     * Remove.
     *
     * @param t the t
     */
    void remove(T t);

    /**
     * Update.
     *
     * @param t the t
     */
    void update(T t);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<T> findAll();

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
    T getById(U u);
}
