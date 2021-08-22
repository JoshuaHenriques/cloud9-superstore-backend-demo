package org.jayhenri.cloud9.customer.service;

import org.jayhenri.cloud9.interfaces.service.customer.CartServiceI;
import org.jayhenri.cloud9.interfaces.service.customer.CustomerServiceI;
import org.jayhenri.cloud9.model.customer.*;
import org.jayhenri.cloud9.model.item.Item;
import org.jayhenri.cloud9.model.login.Login;
import org.jayhenri.cloud9.service.customer.CartService;
import org.jayhenri.cloud9.service.customer.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type Cart service test.
 */
@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    private CartServiceI cartService;

    @Mock
    private CustomerServiceI customerService;

    private Cart cart;

    private Item item1, item2, item3;

    private Customer customer;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        cartService = new CartService(customerService);

        item1 = new Item(
                "iPhone 13 Pro",
                "2021 Model",
                new HashSet<>(),
                1129.99,
                null
        );

        item2 = new Item(
                "Macbook Pro",
                "2021 Model",
                new HashSet<>(),
                2129.99,
                null
        );

        item3 = new Item(
                "iPad Pro",
                "2021 Model",
                new HashSet<>(),
                1929.99,
                null
        );

        Set<Item> items = new HashSet<>();
        items.add(item1);
        items.add(item2);

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
     * Add to cart.
     */
    @Test
    void addToCart() {

        cartService.add(customer, item3);

        assertThat(customer.getCart().getItems().contains(item3)).isTrue();

        assertThat(customer.getCart().getItems().size()).isEqualTo(3);
    }

    /**
     * Remove from cart.
     */
    @Test
    void removeFromCart() {

        customer.getCart().getItems().add(item1);
        customer.getCart().getItems().add(item2);

        cartService.remove(customer, item1.getItemUUID());

        assertThat(customer.getCart().getItems().contains(item1)).isFalse();

        assertThat(customer.getCart().getItems().size()).isEqualTo(1);
    }

    /**
     * Empty cart.
     */
    @Test
    void emptyCart() {

        customer.getCart().getItems().add(item1);

        cartService.empty(customer);

        assertThat(customer.getCart().getItems().size()).isEqualTo(0);
    }

    /**
     * Gets cart.
     */
    @Test
    void getCart() {

        assertThat(cartService.get(customer)).isEqualTo(customer.getCart());
    }

    /**
     * Calculate.
     */
    @Test
    void calculate() {
        assertThat(customer.getCart().getTotal()).isEqualTo(3693.77);
    }
}
