package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The type Item.
 */
// TODO: Implement Ratings
// TODO: There must be a better way to do this
@Getter
@Setter
@NoArgsConstructor
@Embeddable
@Entity
public class Item implements Serializable {

    private static final long serialVersionUID = -496088096515099704L;

    @Column
    private String itemName;

    @Column
    private String description;

    @Column
    private double price;

    @Column
    private byte[] image;

    /**
     * Instantiates a new Item.
     *
     * @param productName the product name
     * @param description the description
     * @param price       the price
     */
    public Item(String productName, String description, double price) {
        this.itemName = productName;
        this.description = description;
        this.price = price;
        this.image = null;
    }
}
