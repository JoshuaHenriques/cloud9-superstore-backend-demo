package org.jayhenri.cloud9.customer.controller;

import org.jayhenri.cloud9.controller.customer.CartController;
import org.jayhenri.cloud9.exception.invalid.InvalidInventoryTypeException;
import org.jayhenri.cloud9.exception.notfound.CustomerNotFoundException;
import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
import org.jayhenri.cloud9.interfaces.controller.customer.CartControllerI;
import org.jayhenri.cloud9.interfaces.service.customer.CartServiceI;
import org.jayhenri.cloud9.interfaces.service.customer.CustomerServiceI;
import org.jayhenri.cloud9.interfaces.service.other.InventoryServiceI;
import org.jayhenri.cloud9.interfaces.service.other.ItemServiceI;
import org.jayhenri.cloud9.model.customer.Address;
import org.jayhenri.cloud9.model.customer.Cart;
import org.jayhenri.cloud9.model.customer.Customer;
import org.jayhenri.cloud9.model.inventory.OnlineInventory;
import org.jayhenri.cloud9.model.inventory.StoreInventory;
import org.jayhenri.cloud9.model.item.Item;
import org.jayhenri.cloud9.model.login.Login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * The type Cart controller test.
 */
@ExtendWith(MockitoExtension.class)
public class CartControllerTest {

    @Captor
    private ArgumentCaptor<Customer> captorCustomer;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    @Captor
    private ArgumentCaptor<Item> captorItem;

    @Mock
    private CustomerServiceI customerService;

    @Mock
    private InventoryServiceI<OnlineInventory> onlineInventoryService;

    @Mock
    private InventoryServiceI<StoreInventory> storeInventoryService;

    @Mock
    private CartServiceI cartService;

    @Mock
    private ItemServiceI itemService;

    private CartControllerI cartController;

    private UUID customerId, itemId;

    private Item item;

    private OnlineInventory onlineInventory;

    private StoreInventory storeInventory;

    private Customer customer;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        customerId = UUID.randomUUID();

        itemId = UUID.randomUUID();

        cartController = new CartController(cartService, customerService, onlineInventoryService, storeInventoryService, itemService);

        item = new Item(
                "iPhone 13 Pro",
                "2021 Model",
                new HashSet<>(),
                1129.99,
                null
        );

        Item item2 = new Item(
                "Macbook Pro",
                "2021 Model",
                new HashSet<>(),
                2129.99,
                null
        );

        Set<Item> items = new HashSet<>();
        items.add(item);
        items.add(item2);

        onlineInventory = new OnlineInventory(
                item,
                item.getItemName(),
                3849,
                item.getPrice()
        );

        storeInventory = new StoreInventory(
                item2,
                item2.getItemName(),
                3901,
                item.getPrice()
        );

        customer = new Customer(
                "customer.mail@gmail.com",
                new Login(),
                new Cart(
                        customer,
                        items
                ),
                new Address(),
                new HashSet<>(),
                new HashSet<>(),
                "John",
                "Doe",
                "6473829338",
                "08/23/1995"
        );
    }

    /**
     * Add online item to cart.
     *
     * @throws InvalidInventoryTypeException the invalid inventory type exception
     * @throws CustomerNotFoundException     the customer not found exception
     * @throws ItemNotFoundException         the item not found exception
     */
    @Test
    void addOnlineItemToCart() throws InvalidInventoryTypeException, CustomerNotFoundException, ItemNotFoundException {

        given(customerService.existsById(customerId)).willReturn(true);
        given(onlineInventoryService.existsById(itemId)).willReturn(true);

        given(customerService.getById(customerId)).willReturn(customer);
        given(onlineInventoryService.getById(itemId)).willReturn(onlineInventory);

        ResponseEntity<String> response = cartController.add(customerId, itemId, "online");

        then(cartService).should().add(captorCustomer.capture(), captorItem.capture());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorItem.getValue()).isEqualTo(onlineInventory.getItem());
    }

    /**
     * Add online item to cart throws item not found exception.
     */
    @Test
    void addOnlineItemToCartThrowsItemNotFoundException() {

        given(customerService.existsById(customerId)).willReturn(true);
        given(onlineInventoryService.existsById(itemId)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> cartController.add(customerId, itemId, "online"));

    }

    /**
     * Add store item to cart.
     *
     * @throws InvalidInventoryTypeException the invalid inventory type exception
     * @throws CustomerNotFoundException     the customer not found exception
     * @throws ItemNotFoundException         the item not found exception
     */
    @Test
    void addStoreItemToCart() throws InvalidInventoryTypeException, CustomerNotFoundException, ItemNotFoundException {

        given(customerService.existsById(customerId)).willReturn(true);
        given(storeInventoryService.existsById(itemId)).willReturn(true);

        given(customerService.getById(customerId)).willReturn(customer);
        given(storeInventoryService.getById(itemId)).willReturn(storeInventory);

        ResponseEntity<String> response = cartController.add(customerId, itemId, "store");

        then(cartService).should().add(captorCustomer.capture(), captorItem.capture());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorItem.getValue()).isEqualTo(storeInventory.getItem());
    }

    /**
     * Add store item to cart throws item not found exception.
     */
    @Test
    void addStoreItemToCartThrowsItemNotFoundException() {

        given(customerService.existsById(customerId)).willReturn(true);
        given(storeInventoryService.existsById(itemId)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> cartController.add(customerId, itemId, "store"));

    }

    /**
     * Add throws invalid inventory type exception.
     */
    @Test
    void addThrowsInvalidInventoryTypeException() {

        given(customerService.existsById(customerId)).willReturn(true);

        assertThrows(InvalidInventoryTypeException.class, () -> cartController.add(customerId, itemId, ""));

    }

    /**
     * Add throws customer not found exception.
     */
    @Test
    void addThrowsCustomerNotFoundException() {

        given(customerService.existsById(customerId)).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> cartController.add(customerId, itemId, ""));
    }

    /**
     * Remove.
     *
     * @throws CustomerNotFoundException the customer not found exception
     * @throws ItemNotFoundException     the item not found exception
     */
    @Test
    void remove() throws CustomerNotFoundException, ItemNotFoundException {

        given(customerService.existsById(customerId)).willReturn(true);
        given(customerService.getById(customerId)).willReturn(customer);
        given(itemService.getById(itemId)).willReturn(item);
        given(cartService.itemExists(customer.getCart(), item)).willReturn(true);

        ResponseEntity<String> response = cartController.delete(itemId, customerId);

        then(cartService).should().remove(captorCustomer.capture(), captorUUID.capture());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorUUID.getValue()).isEqualTo(itemId);
    }

    /**
     * Remove throws item not found exception.
     */
    @Test
    void removeThrowsItemNotFoundException() {

        given(customerService.existsById(customerId)).willReturn(true);
        given(customerService.getById(customerId)).willReturn(customer);
        given(itemService.getById(itemId)).willReturn(item);
        given(cartService.itemExists(customer.getCart(), item)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> cartController.delete(itemId, customerId));
    }

    /**
     * Remove throws customer not found exception.
     */
    @Test
    void removeThrowsCustomerNotFoundException() {

        given(customerService.existsById(customerId)).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> cartController.delete(itemId, customerId));
    }

    /**
     * Empty.
     *
     * @throws CustomerNotFoundException the customer not found exception
     */
    @Test
    void empty() throws CustomerNotFoundException {

        given(customerService.existsById(customerId)).willReturn(true);
        given(customerService.getById(customerId)).willReturn(customer);

        ResponseEntity<String> response = cartController.empty(customerId);

        then(cartService).should().empty(captorCustomer.capture());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(captorCustomer.getValue()).isEqualTo(customer);
    }

    /**
     * Empty throws customer not found exception.
     */
    @Test
    void emptyThrowsCustomerNotFoundException() {

        given(customerService.existsById(customerId)).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> cartController.empty(customerId));
    }

    /**
     * Get.
     *
     * @throws CustomerNotFoundException the customer not found exception
     */
    @Test
    void get() throws CustomerNotFoundException {

        given(customerService.existsById(customerId)).willReturn(true);
        given(customerService.getById(customerId)).willReturn(customer);
        given(cartService.get(customer)).willReturn(customer.getCart());

        ResponseEntity<Cart> response = cartController.get(customerId);

        assertThat(response.getBody()).isEqualTo(customer.getCart());
    }

    /**
     * Gets throws customer not found exception.
     */
    @Test
    void getThrowsCustomerNotFoundException() {

        given(customerService.existsById(customerId)).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> cartController.get(customerId));
    }
}
