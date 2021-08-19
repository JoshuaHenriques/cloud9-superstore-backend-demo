package org.jayhenri.cloud9.service.customer;

import org.jayhenri.cloud9.interfaces.service.customer.CartServiceI;
import org.jayhenri.cloud9.interfaces.service.customer.CustomerServiceI;
import org.jayhenri.cloud9.model.customer.Cart;
import org.jayhenri.cloud9.model.customer.Customer;
import org.jayhenri.cloud9.model.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * The type Cart service.
 */
@Service
public class CartService implements CartServiceI {

    private final CustomerServiceI customerService;

    /**
     * Instantiates a new Cart service.
     *
     * @param customerService the customer service
     */
    @Autowired
    public CartService(CustomerServiceI customerService) {

        this.customerService = customerService;
    }

    /**
     * Add to cart.
     *
     * @param customer the customer
     * @param item     the item
     * @return the customer
     */
    public void add(Customer customer, Item item) {

        customer.getCart().getItems().add(item);
        calculate(customer.getCart());
        customerService.update(customer);
    }

    /**
     * Remove from cart.
     *
     * @param customer the customer
     * @param itemUUID the item uuid
     * @return customer customer
     */
    public void remove(Customer customer, UUID itemUUID) {

        Set<Item> removeMe = new HashSet<>();
        customer.getCart().getItems().forEach(item -> {
            if (item.getItemUUID().equals(itemUUID)) {
                removeMe.add(item);
            }
        });
        customer.getCart().getItems().removeAll(removeMe);
        calculate(customer.getCart());
        customerService.update(customer);
    }

    /**
     * Empty cart.
     *
     * @param customer the customer
     */
    public void empty(Customer customer) {

        customer.getCart().getItems().removeAll(customer.getCart().getItems());
        customer.getCart().setTotal(0.00);

        customerService.update(customer);
    }

    /**
     * Gets cart.
     *
     * @param customer the customer
     * @return the cart
     */
    public Cart get(Customer customer) {

        return customer.getCart();
    }

    public boolean itemExists(Cart cart, Item item){
        return cart.getItems().contains(item);
    }

    // todo: test
    public void calculate(Cart cart) {

        cart.getItems().forEach(item -> {
            cart.setTotal(cart.getTotal() + item.getPrice());
        });

        double DELIVERY = 9.99;
        double HST = 1.13;

        cart.setTotal(cart.getTotal()*HST*DELIVERY);
    }
}
