package org.jayhenri.cloud9.controller.customer;

import org.jayhenri.cloud9.exception.invalid.InvalidInventoryTypeException;
import org.jayhenri.cloud9.exception.notfound.CustomerNotFoundException;
import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
import org.jayhenri.cloud9.interfaces.controller.customer.CartControllerI;
import org.jayhenri.cloud9.interfaces.service.customer.CartServiceI;
import org.jayhenri.cloud9.interfaces.service.customer.CustomerServiceI;
import org.jayhenri.cloud9.interfaces.service.other.InventoryServiceI;
import org.jayhenri.cloud9.model.customer.Cart;
import org.jayhenri.cloud9.model.inventory.OnlineInventory;
import org.jayhenri.cloud9.model.inventory.StoreInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * The type Cart controller.
 */
@RestController
@RequestMapping("/api/customer/cart")
public class CartController implements CartControllerI {

    private final CustomerServiceI customerService;
    private final InventoryServiceI<OnlineInventory> onlineInventoryService;
    private final InventoryServiceI<StoreInventory> storeInventoryService;
    private final CartServiceI cartService;

    /**
     * Instantiates a new Customer controller.
     *
     * @param cartService            the cart service
     * @param customerService        the customer service
     * @param onlineInventoryService the inventory service
     */
    @Autowired
    public CartController(CartServiceI cartService, CustomerServiceI customerService, InventoryServiceI<OnlineInventory> onlineInventoryService, InventoryServiceI<StoreInventory> storeInventoryService) {
        this.customerService = customerService;
        this.cartService = cartService;
        this.onlineInventoryService = onlineInventoryService;
        this.storeInventoryService = storeInventoryService;
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
    @PutMapping(value = "/{customerId}/cart/add/{type}/{itemId}")
    public ResponseEntity<String> add(@PathVariable UUID customerId, @PathVariable UUID itemId, @PathVariable String type)
            throws CustomerNotFoundException, ItemNotFoundException, InvalidInventoryTypeException {
        if (customerService.existsById(customerId)) {
            switch (type) {
                case "online":
                    if (onlineInventoryService.existsById(itemId)) {

                        cartService.add(customerService.getById(customerId), onlineInventoryService.getById(itemId).getItem());

                        HttpHeaders responseHeaders = new HttpHeaders();
                        responseHeaders.set("CustomerController", "add");
                        return new ResponseEntity<>("Successfully Added to Cart", responseHeaders, HttpStatus.CREATED);
                    } else
                        throw new ItemNotFoundException();

                case "store":
                    if (storeInventoryService.existsById(itemId)) {

                        cartService.add(customerService.getById(customerId), storeInventoryService.getById(itemId).getItem());

                        HttpHeaders responseHeaders = new HttpHeaders();
                        responseHeaders.set("CustomerController", "add");
                        return new ResponseEntity<>("Successfully Added to Cart", responseHeaders, HttpStatus.CREATED);
                    } else
                        throw new ItemNotFoundException();

                default:
                    throw new InvalidInventoryTypeException();
            }
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
    public ResponseEntity<String> remove(@PathVariable UUID itemId, @PathVariable UUID customerId)
            throws CustomerNotFoundException, ItemNotFoundException {
        if (customerService.existsById(customerId)) {
            if (onlineInventoryService.existsById(itemId)) {
                cartService.remove(customerService.getById(customerId), itemId);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("CustomerController", "remove");
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
    public ResponseEntity<String> empty(@PathVariable UUID customerId) throws CustomerNotFoundException {
        if (customerService.existsById(customerId)) {
            cartService.empty(customerService.getById(customerId));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("CustomerController", "empty");
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
    public ResponseEntity<Cart> get(@PathVariable UUID customerId) throws CustomerNotFoundException {
        if (customerService.existsById(customerId)) {
            Cart _cart = cartService.get(customerService.getById(customerId));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("CustomerController", "get");
            return new ResponseEntity<>(_cart, responseHeaders, HttpStatus.OK);
        } else
            throw new CustomerNotFoundException();
    }
}
