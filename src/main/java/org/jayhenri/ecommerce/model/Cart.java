package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.UUID;
import org.javatuples.Triplet;

// TODO: Implement HST+GST+PROMOCODE computation

@Getter
@Setter
@NoArgsConstructor
public class Cart {

    @Id
    private UUID uuid;

    private ArrayList<Triplet<Item, Integer, Character>> items;

    private Double subTotal;

    private Double total;

    private static final Double HST = 0.13;

    private static final Double DELIVERY_FEE = 10.00;

    public Cart(UUID uuid) {
        this.uuid = uuid;
    }

    public void addToCart(Item item, Integer quantity, Character size) {
        items.add(new Triplet<Item, Integer, Character>(item, quantity, size));
        update();
    }

    public void removeFromCart(String itemName) {
        items.forEach(item -> {
            if (item.getValue0().getName().equals(itemName)) {
                items.remove(item);
            }
        });
        update();
    }

    public void update() {
        items.forEach(item -> {
            subTotal += item.getValue0().getPrice();
        });
        total = subTotal*HST + DELIVERY_FEE;
    }
}