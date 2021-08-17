package org.jayhenri.cloud9.interfaces.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.UUID;

/**
 * The interface Controller i.
 *
 * @param <T> the type parameter
 */
public interface ControllerI<T> {

    /**
     * Add.
     *
     * @param t the t
     */
    @PostMapping("/add/")
    void add(T t);

    /**
     * Remove.
     *
     * @param t the t
     */
    @DeleteMapping("/remove/")
    void remove(T t);

    /**
     * Update.
     *
     * @param t    the t
     * @param uuid the uuid
     */
    @PutMapping("/update/{uuid}")
    void update(T t, UUID uuid);

    /**
     * Find all list.
     *
     * @return the list
     */
    @GetMapping("/list/")
    List<T> findAll();

    /**
     * Exists by id boolean.
     *
     * @param uuid the u
     * @return the boolean
     */
    @GetMapping("/exists/{uuid}")
    boolean existsById(UUID uuid);

    /**
     * Gets by id.
     *
     * @param uuid the u
     * @return the by id
     */
    @GetMapping("/get/{uuid}")
    T getById(UUID uuid);
}
