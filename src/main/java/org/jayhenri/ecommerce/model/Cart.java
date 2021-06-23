package org.jayhenri.ecommerce.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.UUID;
import org.javatuples.Triplet;

// TODO: Implement HST+GST+PROMOCODE computation

@Getter
@Setter
@NoArgsConstructor
public class Cart {


    private UUID uuid;

    private ArrayList<Triplet<Item, Integer, Character>> items;

    public Cart(UUID uuid) {

        this.uuid = uuid;
        this.items = new ArrayList<>();
    }

    public void setItems(ArrayList<Triplet<Item, Integer, Character>> items) {
        this.items = items;
    }

}