package org.jayhenri.store_manager.interfaces.service;

import java.util.List;
import java.util.UUID;

/**
 * The interface Service i.
 *
 * @param <T> the type parameter
 */
public interface ServiceI<T> {

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
     * @param uuid the u
     * @return the boolean
     */
    boolean existsById(UUID uuid);

    /**
     * Gets by id.
     *
     * @param uuid the u
     * @return the by id
     */
    T getById(UUID uuid);
}
