package org.jayhenri.cloud9.customer.controller;

import org.jayhenri.cloud9.controller.customer.CartController;
import org.jayhenri.cloud9.exception.invalid.InvalidInventoryTypeException;
import org.jayhenri.cloud9.exception.notfound.CustomerNotFoundException;
import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
import org.jayhenri.cloud9.interfaces.controller.customer.CartControllerI;
import org.jayhenri.cloud9.interfaces.service.customer.CartServiceI;
import org.jayhenri.cloud9.interfaces.service.customer.CustomerServiceI;
import org.jayhenri.cloud9.interfaces.service.other.InventoryServiceI;
import org.jayhenri.cloud9.model.customer.*;
import org.jayhenri.cloud9.model.inventory.OnlineInventory;
import org.jayhenri.cloud9.model.inventory.StoreInventory;
import org.jayhenri.cloud9.model.item.Item;
import org.jayhenri.cloud9.model.login.Login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.mockito.BDDMockito.given;

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

    private Customer customer;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        uuid = UUID.randomUUID();

        uuid2 = UUID.randomUUID();

        cartController = new CartController(cartService, customerService, onlineInventoryService, storeInventoryService);

        item = new Item(
                "iPhone 13 Pro",
                "2021 Model",
                new HashSet<>(),
                null
        );

        item2 = new Item(
                "iPhone 13 Pro",
                "2021 Model",
                new HashSet<>(),
                null
        );

        cart = new Cart(
                new Customer(),
                new HashSet<Item>(),
                1129.99
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

    void addOnlineItemToCart() throws InvalidInventoryTypeException, CustomerNotFoundException, ItemNotFoundException {

        given(customerService.existsById(uuid)).willReturn(true);
        given(onlineInventoryService.existsById(uuid)).willReturn(true);

        cartController.add(uuid, uuid2, "online");
    }
}
