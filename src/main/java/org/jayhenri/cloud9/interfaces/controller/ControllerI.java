package org.jayhenri.cloud9.interfaces.controller;

import org.jayhenri.cloud9.exception.alreadyexists.ItemAlreadyExistsException;
import org.jayhenri.cloud9.exception.alreadyexists.LoginAlreadyExistsException;
import org.jayhenri.cloud9.exception.alreadyexists.StoreAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidItemException;
import org.jayhenri.cloud9.exception.invalid.InvalidLoginException;
import org.jayhenri.cloud9.exception.invalid.InvalidPostalCodeException;
import org.jayhenri.cloud9.exception.invalid.InvalidStoreException;
import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
import org.jayhenri.cloud9.exception.notfound.LoginNotFoundException;
import org.jayhenri.cloud9.exception.notfound.StoreNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import java.util.List;
import java.util.UUID;

/**
 * The interface Controller i.
 *
 * @param <T> the type parameter
 */
public interface ControllerI<T> {

    /**
     * Add response entity.
     *
     * @param t the t
     * @return the response entity
     * @throws LoginAlreadyExistsException the login already exists exception
     * @throws InvalidLoginException       the invalid login exception
     * @throws StoreAlreadyExistsException the store already exists exception
     * @throws InvalidPostalCodeException  the invalid postal code exception
     * @throws InvalidStoreException       the invalid store exception
     * @throws ItemAlreadyExistsException  the item already exists exception
     * @throws InvalidItemException        the invalid item exception
     */
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> add(@RequestBody T t) throws LoginAlreadyExistsException, InvalidLoginException, StoreAlreadyExistsException, InvalidPostalCodeException, InvalidStoreException, ItemAlreadyExistsException, InvalidItemException;

    /**
     * Update response entity.
     *
     * @param t    the t
     * @param uuid the uuid
     * @return the response entity
     * @throws InvalidLoginException      the invalid login exception
     * @throws LoginNotFoundException     the login not found exception
     * @throws InvalidStoreException      the invalid store exception
     * @throws StoreNotFoundException     the store not found exception
     * @throws InvalidItemException       the invalid item exception
     * @throws ItemNotFoundException      the item not found exception
     * @throws ItemAlreadyExistsException the item already exists exception
     */
    @PutMapping(value = "/update/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> update(@RequestBody T t, @PathVariable UUID uuid) throws InvalidLoginException, LoginNotFoundException, InvalidStoreException, StoreNotFoundException, InvalidItemException, ItemNotFoundException, ItemAlreadyExistsException;

    /**
     * Delete response entity.
     *
     * @param uuid the uuid
     * @return the response entity
     * @throws LoginNotFoundException the login not found exception
     * @throws StoreNotFoundException the store not found exception
     * @throws ItemNotFoundException  the item not found exception
     */
    @DeleteMapping(value = "/delete/{uuid}")
    ResponseEntity<String> delete(@PathVariable UUID uuid) throws LoginNotFoundException, StoreNotFoundException, ItemNotFoundException;

    /**
     * List response entity.
     *
     * @return the response entity
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<T>> list();

    /**
     * Get response entity.
     *
     * @param uuid the uuid
     * @return the response entity
     * @throws InvalidNameException   the invalid name exception
     * @throws InvalidLoginException  the invalid login exception
     * @throws LoginNotFoundException the login not found exception
     * @throws StoreNotFoundException the store not found exception
     * @throws InvalidStoreException  the invalid store exception
     * @throws ItemNotFoundException  the item not found exception
     * @throws InvalidItemException   the invalid item exception
     */
    @GetMapping(value = "/get/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<T> get(@PathVariable UUID uuid) throws InvalidNameException, InvalidLoginException, LoginNotFoundException, StoreNotFoundException, InvalidStoreException, ItemNotFoundException, InvalidItemException;
}
