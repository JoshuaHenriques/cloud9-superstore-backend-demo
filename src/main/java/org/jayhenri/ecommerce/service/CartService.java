package org.jayhenri.ecommerce.service;

import org.javatuples.Quartet;
import org.jayhenri.ecommerce.model.Customer;
import org.jayhenri.ecommerce.model.Item;
import org.jayhenri.ecommerce.model.Cart;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartService {

    private Customer customer;

    private static final Double HST = 0.13;
    private static final Double DELIVERY_FEE = 9.99;

    private Cart cart;

    private Double subTotal = 0.00;
    private Double total = 0.00;

    public CartService(Customer customer) {
        this.customer = customer;
        this.customer.setCart(new Cart());
    }

    public void addToCart(UUID uuid, Item item, Integer quantity, Character size) {
        cart.getItems().add(new Quartet<>(uuid, item, quantity, size));
        update();
    }

    public void removeFromCart(String itemName) {
        cart.getItems().forEach(item -> {
//            if (item.getValue1().getName().equals(itemName)) {
//                cart.getItems().remove(item);
//            }
        });
        update();
    }

    public void update() {
        cart.getItems().forEach(item -> {
            subTotal += item.getValue1().getPrice();
        });
        total = subTotal * HST + DELIVERY_FEE;
    }
}

