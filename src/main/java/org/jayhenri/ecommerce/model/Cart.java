package org.jayhenri.ecommerce.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.UUID;
import org.javatuples.Quartet;

import javax.persistence.Embeddable;

// TODO: Implement HST+GST+PROMOCODE computation

@Getter
@Setter
@Embeddable
public class Cart {

    private ArrayList<Quartet<UUID, Item, Integer, Character>> items;

    public Cart() {
        this.items = new ArrayList<>();
    }
}