package org.jayhenri.cloud9.controller.customer;

import org.jayhenri.cloud9.exception.notfound.CustomerNotFoundException;
import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
import org.jayhenri.cloud9.model.customer.Cart;
import org.jayhenri.cloud9.service.customer.CartService;
import org.jayhenri.cloud9.service.customer.CustomerService;
import org.jayhenri.cloud9.service.inventory.OnlineInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

/**
 * The type Cart controller.
 */
public class CartController {

    private final CustomerService customerService;
    private final OnlineInventoryService onlineInventoryService;
    private final CartService cartService;

    /**
     * Instantiates a new Customer controller.
     *
     * @param cartService            the cart service
     * @param customerService        the customer service
     * @param onlineInventoryService the inventory service
     */
    @Autowired
    public CartController(CartService cartService, CustomerService customerService, OnlineInventoryService onlineInventoryService) {
        this.customerService = customerService;
        this.cartService = cartService;
        this.onlineInventoryService = onlineInventoryService;
    }

    /**
     * Add to cart.
     *
     * @param customerId the customer id
     * @param itemId     the item id
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     * @throws ItemNotFoundException     the item not found exception
     */
    @PutMapping(value = "/{customerId}/cart/add/{itemId}")
    public ResponseEntity<String> addToCart(@PathVariable UUID customerId, @PathVariable UUID itemId)
            throws CustomerNotFoundException, ItemNotFoundException {
        if (customerService.existsById(customerId)) {
            if (onlineInventoryService.existsById(itemId)) {

                cartService.addToCart(customerService.getById(customerId), onlineInventoryService.getById(itemId).getItem());

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("CustomerController", "addToCart");
                return new ResponseEntity<>("Successfully Added to Cart", responseHeaders, HttpStatus.CREATED);
            } else
                throw new ItemNotFoundException();
        } else
            throw new CustomerNotFoundException();
    }

    /**
     * Remove from cart.
     *
     * @param itemId     the email
     * @param customerId the customer id
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     * @throws ItemNotFoundException     the item not found exception
     */
    @DeleteMapping(value = "/{customerId}/cart/remove/{itemID}")
    public ResponseEntity<String> removeFromCart(@PathVariable UUID itemId, @PathVariable UUID customerId)
            throws CustomerNotFoundException, ItemNotFoundException {
        if (customerService.existsById(customerId)) {
            if (onlineInventoryService.existsById(itemId)) {
                cartService.removeFromCart(customerService.getById(customerId), itemId);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("CustomerController", "removeFromCart");
                return new ResponseEntity<>("Successfully Removed from Cart", responseHeaders, HttpStatus.OK);
            } else
                throw new ItemNotFoundException();
        } else
            throw new CustomerNotFoundException();
    }

    /**
     * Empty cart.
     *
     * @param customerId the customer id
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     */
    @PutMapping(value = "/{customerId}/cart/empty")
    public ResponseEntity<String> emptyCart(@PathVariable UUID customerId) throws CustomerNotFoundException {
        if (customerService.existsById(customerId)) {
            cartService.emptyCart(customerService.getById(customerId));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("CustomerController", "emptyCart");
            return new ResponseEntity<>("Successfully Emptied Cart", responseHeaders, HttpStatus.OK);
        } else
            throw new CustomerNotFoundException();
    }

    /**
     * Gets cart.
     *
     * @param customerId the customer id
     * @return the cart
     * @throws CustomerNotFoundException the customer not found exception
     */
    @GetMapping(value = "/{customerId}/cart/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cart> getCart(@PathVariable UUID customerId) throws CustomerNotFoundException {
        if (customerService.existsById(customerId)) {
            Cart _cart = cartService.getCart(customerService.getById(customerId));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("CustomerController", "getCart");
            return new ResponseEntity<>(_cart, responseHeaders, HttpStatus.OK);
        } else
            throw new CustomerNotFoundException();
    }
}
