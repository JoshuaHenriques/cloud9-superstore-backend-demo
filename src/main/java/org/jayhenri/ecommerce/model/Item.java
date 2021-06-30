package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// TODO: Implement Ratings
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Item {

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private double price;

    public Item(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
