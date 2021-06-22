package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.UUID;

// TODO: Implement HST+GST+PROMOCODE computation

@Getter
@Setter
@NoArgsConstructor
public class Cart {

    @Id
    private UUID uuid;

    private ArrayList<Item> items;

    private Double subTotal;

    private Double total;

    private final Double HST = 0.13;

    public Cart(UUID uuid, ArrayList<Item> items) {
        this.uuid = uuid;
        this.items = items;
    }
}