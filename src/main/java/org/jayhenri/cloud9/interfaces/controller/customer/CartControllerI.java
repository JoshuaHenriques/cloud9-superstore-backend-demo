package org.jayhenri.cloud9.interfaces.controller.customer;

import org.jayhenri.cloud9.exception.invalid.InvalidInventoryTypeException;
import org.jayhenri.cloud9.exception.notfound.CustomerNotFoundException;
import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
import org.jayhenri.cloud9.model.customer.Cart;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * The interface Cart controller i.
 */
@RequestMapping("/api/customer/cart")
public interface CartControllerI {

    /**
     * Add response entity.
     *
     * @param customerId the customer id
     * @param itemId     the item id
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     * @throws ItemNotFoundException     the item not found exception
     */
    @PutMapping(value = "/{customerId}/cart/add/{itemId}")
    ResponseEntity<String> add(@PathVariable UUID customerId, @PathVariable UUID itemId, @PathVariable String type) throws CustomerNotFoundException, ItemNotFoundException, InvalidInventoryTypeException;

    /**
     * Remove response entity.
     *
     * @param itemId     the item id
     * @param customerId the customer id
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     * @throws ItemNotFoundException     the item not found exception
     */
    @DeleteMapping(value = "/{customerId}/cart/remove/{itemID}")
    ResponseEntity<String> remove(@PathVariable UUID itemId, @PathVariable UUID customerId) throws CustomerNotFoundException, ItemNotFoundException;

    /**
     * Empty response entity.
     *
     * @param customerId the customer id
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     */
    @PutMapping(value = "/{customerId}/cart/empty")
    ResponseEntity<String> empty(@PathVariable UUID customerId) throws CustomerNotFoundException;

    /**
     * Get response entity.
     *
     * @param customerId the customer id
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     */
    @GetMapping(value = "/{customerId}/cart/get", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Cart> get(@PathVariable UUID customerId) throws CustomerNotFoundException;
}
