package org.jayhenri.cloud9.interfaces.controller.other;

import org.jayhenri.cloud9.exception.alreadyexists.ItemAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidItemException;
import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * The interface Inventory controller i.
 *
 * @param <T> the type parameter
 */
public interface InventoryControllerI<T> {

    /**
     * Add response entity.
     *
     * @param t      the t
     * @param itemId the item id
     * @return the response entity
     * @throws ItemAlreadyExistsException the item already exists exception
     * @throws InvalidItemException       the invalid item exception
     */
    @PostMapping(value = "/add/{itemId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<String> add(@RequestBody @ModelAttribute T t, @PathVariable UUID itemId)
            throws ItemAlreadyExistsException, InvalidItemException;

    /**
     * Update response entity.
     *
     * @param t           the t
     * @param inventoryId the inventory id
     * @return the response entity
     * @throws InvalidItemException  the invalid item exception
     * @throws ItemNotFoundException the item not found exception
     */
    @PutMapping(value = "/update/{inventoryId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<String> update(@RequestBody @ModelAttribute T t, @PathVariable UUID inventoryId)
            throws InvalidItemException, ItemNotFoundException;

    /**
     * Get response entity.
     *
     * @param itemName the item name
     * @return the response entity
     * @throws ItemNotFoundException the item not found exception
     */
    @GetMapping(value = "/get/{itemName}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<T> getByItemName(@PathVariable String itemName) throws ItemNotFoundException;

    /**
     * Get response entity.
     *
     * @param itemId the item name
     * @return the response entity
     * @throws ItemNotFoundException the item not found exception
     */
    @GetMapping(value = "/get/{inventoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<T> getById(@PathVariable UUID itemId) throws ItemNotFoundException;

    /**
     * Remove response entity.
     *
     * @param itemId the item name
     * @return the response entity
     * @throws InvalidItemException  the invalid item exception
     * @throws ItemNotFoundException the item not found exception
     */
    @DeleteMapping(value = "/delete/{itemId}")
    ResponseEntity<String> delete(@PathVariable UUID itemId)
            throws InvalidItemException, ItemNotFoundException;

    /**
     * Find all response entity.
     *
     * @return the response entity
     */
    @GetMapping(value = "/list")
    ResponseEntity<List<T>> findAll();
}
