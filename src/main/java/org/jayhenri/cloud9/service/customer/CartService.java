package org.jayhenri.cloud9.service.customer;

import org.jayhenri.cloud9.model.customer.Cart;
import org.jayhenri.cloud9.model.customer.Customer;
import org.jayhenri.cloud9.model.store.Item;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * The type Cart service.
 */
@Service
public class CartService {

    /**
     * Add to cart.
     *
     * @param customer the customer
     * @param item     the item
     * @return the customer
     */
    public Customer addToCart(Customer customer, Item item) {

        customer.getCart().getItems().add(item);
        return customer;
    }

    /**
     * Remove from cart.
     *
     * @param customer    the customer
     * @param productName the product name
     * @return customer
     */
    public Customer removeFromCart(Customer customer, String productName) {

        Set<Item> removeMe = new HashSet<>();
        customer.getCart().getItems().forEach(item -> {
            if (item.getItemName().equals(productName)) {
                removeMe.add(item);
            }
        });
        customer.getCart().getItems().removeAll(removeMe);
        return customer;
    }

    /**
     * Empty cart.
     *
     * @param customer the customer
     * @return customer
     */
    public Customer emptyCart(Customer customer) {

        customer.getCart().setItems(new HashSet<>(customer.getCart().getItems()));
        customer.getCart().setTotal(0.00);

        return customer;
    }

    /**
     * Gets cart.
     *
     * @param customer the customer
     * @return the cart
     */
    public Cart getCart(Customer customer) {

        return customer.getCart();
    }
}
