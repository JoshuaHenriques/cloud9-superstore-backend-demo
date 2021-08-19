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
import org.jayhenri.cloud9.model.customer.*;
import org.jayhenri.cloud9.model.inventory.OnlineInventory;
import org.jayhenri.cloud9.model.inventory.StoreInventory;
import org.jayhenri.cloud9.model.item.Item;
import org.jayhenri.cloud9.model.login.Login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

@ExtendWith(MockitoExtension.class)
public class CartControllerTest {

    @Captor
    private ArgumentCaptor<Cart> captorCart;

    @Captor
    private ArgumentCaptor<Customer> captorCustomer;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    @Captor
    private ArgumentCaptor<UUID> captorUUID2;

    @Captor
    private ArgumentCaptor<String> captorString;

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

    private CartControllerI cartController;

    private Cart cart;

    private UUID uuid, uuid2;

    private Item item, item2;

    private Set<Item> items;

    @Mock
    private ItemServiceI itemService;

    private OnlineInventory onlineInventory;

    private StoreInventory storeInventory;

    private Customer customer;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        uuid = UUID.randomUUID();

        uuid2 = UUID.randomUUID();

        cartController = new CartController(cartService, customerService, onlineInventoryService, storeInventoryService, itemService);

        item = new Item(
                "iPhone 13 Pro",
                "2021 Model",
                new HashSet<>(),
                null
        );

        item2 = new Item(
                "Macbook Pro",
                "2021 Model",
                new HashSet<>(),
                null
        );

        Set<Item> items = new HashSet<>();
        items.add(item);
        items.add(item2);

        onlineInventory = new OnlineInventory(
                item,
                item.getItemName(),
                3849,
                1129.99
        );

        storeInventory = new StoreInventory(
                item2,
                item2.getItemName(),
                3901,
                2129.99
        );

        cart = new Cart(
                new Customer(),
                items,
                0.00
        );

        customer = new Customer(
                "customer.mail@gmail.com",
                new Login(),
                cart,
                new Address(),
                new HashSet<CreditCard>(),
                new HashSet<Orders>(),
                "John",
                "Doe",
                "6473829338",
                "08/23/1995"
        );
    }

    @Test
    void addOnlineItemToCart() throws InvalidInventoryTypeException, CustomerNotFoundException, ItemNotFoundException {

        given(customerService.existsById(uuid)).willReturn(true);
        given(onlineInventoryService.existsById(uuid2)).willReturn(true);

        given(customerService.getById(uuid)).willReturn(customer);
        given(onlineInventoryService.getById(uuid2)).willReturn(onlineInventory);

        ResponseEntity<String> response = cartController.add(uuid, uuid2, "online");

        then(cartService).should().add(captorCustomer.capture(), captorItem.capture());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorItem.getValue()).isEqualTo(onlineInventory.getItem());
    }

    @Test
    void addOnlineItemToCartThrowsItemNotFoundException() {

        given(customerService.existsById(uuid)).willReturn(true);
        given(onlineInventoryService.existsById(uuid2)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> {
            cartController.add(uuid, uuid2, "online");
        });

    }

    @Test
    void addStoreItemToCart() throws InvalidInventoryTypeException, CustomerNotFoundException, ItemNotFoundException {

        given(customerService.existsById(uuid)).willReturn(true);
        given(storeInventoryService.existsById(uuid2)).willReturn(true);

        given(customerService.getById(uuid)).willReturn(customer);
        given(storeInventoryService.getById(uuid2)).willReturn(storeInventory);

        ResponseEntity<String> response = cartController.add(uuid, uuid2, "store");

        then(cartService).should().add(captorCustomer.capture(), captorItem.capture());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorItem.getValue()).isEqualTo(storeInventory.getItem());
    }

    @Test
    void addStoreItemToCartThrowsItemNotFoundException() {

        given(customerService.existsById(uuid)).willReturn(true);
        given(storeInventoryService.existsById(uuid2)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> {
            cartController.add(uuid, uuid2, "store");
        });

    }

    @Test
    void addThrowsInvalidInventoryTypeException() {

        given(customerService.existsById(uuid)).willReturn(true);

        assertThrows(InvalidInventoryTypeException.class, () -> {
            cartController.add(uuid, uuid2, "");
        });

    }

    @Test
    void addThrowsCustomerNotFoundException() {

        given(customerService.existsById(uuid)).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
            cartController.add(uuid, uuid2, "");
        });
    }

    @Test
    void remove() throws CustomerNotFoundException, ItemNotFoundException {

        given(customerService.existsById(uuid)).willReturn(true);
        given(customerService.getById(uuid).getCart()).willReturn(cart);
        given(itemService.getById(uuid2)).willReturn(item);
        given(cartService.itemExists(cart, item)).willReturn(true);

        ResponseEntity<String> response =  cartController.remove(uuid2, uuid);

        then(cartService).should().remove(captorCustomer.capture(), captorUUID.capture());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorUUID.getValue()).isEqualTo(uuid2);
    }
}
