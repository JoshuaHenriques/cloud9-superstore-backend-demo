package org.jayhenri.cloud9.customer.service;

import org.jayhenri.cloud9.model.customer.*;
import org.jayhenri.cloud9.model.item.Item;
import org.jayhenri.cloud9.model.item.Review;
import org.jayhenri.cloud9.model.login.Login;
import org.jayhenri.cloud9.service.customer.AddressService;
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

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    private CartService cartService;

    @Mock
    private CustomerService customerService;

    @Captor
    private ArgumentCaptor<Cart> captorCart;

    private Cart cart;
    
    private Item item1, item2;
    
    private Set<Item> items;
    
    private Customer customer;
    
    @BeforeEach
    void setUp() {

        cartService = new CartService(customerService);
    
        item1 = new Item(
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
    /**
     * Add to cart.
     */
    @Test
    void addToCart() {

        cartService.addToCart(customer, item1);

        assertThat(customer.getCart().getItems().contains(item1)).isTrue();

        assertThat(customer.getCart().getItems().size()).isEqualTo(1);
    }

    /**
     * Remove from cart.
     */
    @Test
    void removeFromCart() {

        customer.getCart().getItems().add(item1);
        customer.getCart().getItems().add(item2);

        cartService.removeFromCart(customer, item1.getItemUUID());

        assertThat(customer.getCart().getItems().contains(item1)).isFalse();

        assertThat(customer.getCart().getItems().size()).isEqualTo(1);
    }

    /**
     * Empty cart.
     */
    @Test
    void emptyCart() {

        customer.getCart().getItems().add(item1);

        cartService.emptyCart(customer);

        assertThat(customer.getCart().getItems().size()).isEqualTo(0);
    }

    /**
     * Gets cart.
     */
    @Test
    void getCart() {

        assertThat(cartService.getCart(customer)).isEqualTo(customer.getCart());
    }
}
