package org.jayhenri.ecommerce.service;

import org.javatuples.Triplet;
import org.jayhenri.ecommerce.model.Item;
import org.jayhenri.ecommerce.model.Cart;

import java.util.UUID;

public class CartUpdateService {

    private static final Double HST = 0.13;
    private static final Double DELIVERY_FEE = 10.00;

    private Cart cart;
    private UUID uuid;

    private Double subTotal = 0.00;
    private Double total = 0.00;

    public CartUpdateService(UUID uuid) {
        this.uuid = uuid;
        cart = new Cart(uuid);
    }

    public void addToCart(Item item, Integer quantity, Character size) {
        cart.getItems().add(new Triplet<Item, Integer, Character>(item, quantity, size));
        update();
    }

    public void removeFromCart(String itemName) {
        cart.getItems().forEach(item -> {
            if (item.getValue0().getName().equals(itemName)) {
                cart.getItems().remove(item);
            }
        });
        update();
    }

    public void update() {
        cart.getItems().forEach(item -> {
            subTotal += item.getValue0().getPrice();
        });
        total = subTotal * HST + DELIVERY_FEE;
    }
}

