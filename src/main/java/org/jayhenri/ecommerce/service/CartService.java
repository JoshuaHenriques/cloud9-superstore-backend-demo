package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.model.Item;
import org.jayhenri.ecommerce.model.Cart;

import org.springframework.stereotype.Service;

// TODO: Update Customer through CustomerService methods
@Service
public class CartService {

    private Cart cart;

    private Item item;

    public CartService(String customerEmail) {
        this.cart.setCustomerEmail(customerEmail);
    }

    public void addToCart(Item item) {
        this.cart.getItems().add(item);
        update();
    }

    public void removeFromCart(Item item) {
        this.cart.getItems().remove(item);
        update();
    }

    public void update() {
        double total = cart.getItems().stream().mapToDouble(Item::getPrice).sum();
        cart.setTotal(total);
    }

    public void saveToCustomer() {}
}

