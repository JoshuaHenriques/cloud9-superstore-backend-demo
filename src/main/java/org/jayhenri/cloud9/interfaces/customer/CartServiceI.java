package org.jayhenri.cloud9.interfaces.customer;

/**
 * The interface Cart service i.
 *
 * @param <T> the type parameter
 * @param <C> the type parameter
 * @param <S> the type parameter
 * @param <U> the type parameter
 */
public interface CartServiceI<T, C, S, U> {

    /**
     * Add.
     *
     * @param t the t
     * @param c the c
     */
    void add(T t, C c);

    /**
     * Remove.
     *
     * @param t the t
     * @param u the u
     */
    void remove(T t, U u);

    /**
     * Empty.
     *
     * @param t the t
     */
    void empty(T t);

    /**
     * Get s.
     *
     * @param t the t
     * @return the s
     */
    S get(T t);
}
