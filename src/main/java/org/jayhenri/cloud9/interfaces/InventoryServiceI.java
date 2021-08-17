package org.jayhenri.cloud9.interfaces;

import java.util.List;

/**
 * The interface Inventory service i.
 *
 * @param <T> the type parameter
 * @param <C> the type parameter
 * @param <U> the type parameter
 */
public interface InventoryServiceI<T, C, U> {

    /**
     * Add.
     *
     * @param c        the c
     * @param quantity the quantity
     * @param price    the price
     */
    void add(C c, int quantity, double price);

    /**
     * Update.
     *
     * @param t the t
     */
    void update(T t);

    /**
     * Delete.
     *
     * @param t the t
     */
    void delete(T t);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<T> findAll();

    /**
     * Exists by item name boolean.
     *
     * @param itemName the item name
     * @return the boolean
     */
    boolean existsByItemName(String itemName);

    /**
     * Gets by item name.
     *
     * @param itemName the item name
     * @return the by item name
     */
    T getByItemName(String itemName);

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
