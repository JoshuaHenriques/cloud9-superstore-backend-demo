package org.jayhenri.store_manager.interfaces.controller.other;

import org.jayhenri.store_manager.exception.alreadyexists.InventoryAlreadyExistsException;
import org.jayhenri.store_manager.exception.invalid.InvalidInventoryException;
import org.jayhenri.store_manager.exception.invalid.InvalidItemException;
import org.jayhenri.store_manager.exception.notfound.ItemNotFoundException;
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
     * @throws InventoryAlreadyExistsException the inventory already exists exception
     * @throws InvalidItemException            the invalid item exception
     * @throws ItemNotFoundException           the item not found exception
     * @throws InvalidInventoryException       the invalid inventory exception
     */
    @PostMapping(value = "/add/{itemId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> add(@RequestBody T t, @PathVariable UUID itemId)
            throws InventoryAlreadyExistsException, InvalidItemException, ItemNotFoundException, InvalidInventoryException;

    /**
     * Update response entity.
     *
     * @param t           the t
     * @param inventoryId the inventory id
     * @return the response entity
     * @throws InvalidItemException  the invalid item exception
     * @throws ItemNotFoundException the item not found exception
     */
    @PutMapping(value = "/update/{inventoryId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> update(@RequestBody T t, @PathVariable UUID inventoryId)
            throws InvalidItemException, ItemNotFoundException;

    /**
     * Gets by item name.
     *
     * @param itemName the item name
     * @return the by item name
     * @throws ItemNotFoundException the item not found exception
     */
    @GetMapping(value = "/get/{itemName}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<T> getByItemName(@PathVariable String itemName) throws ItemNotFoundException;

    /**
     * Gets by id.
     *
     * @param itemId the item id
     * @return the by id
     * @throws ItemNotFoundException the item not found exception
     */
    @GetMapping(value = "/get/{inventoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<T> getById(@PathVariable UUID itemId) throws ItemNotFoundException;

    /**
     * Delete response entity.
     *
     * @param itemId the item id
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
