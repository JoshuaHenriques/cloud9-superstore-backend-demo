package org.jayhenri.cloud9.model.item;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Item.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "item")
public class Item implements Serializable {

    private static final long serialVersionUID = -496088096515099704L;

    @Id
    @Column(name = "item_id", nullable = false)
    private UUID itemUUID = UUID.randomUUID();

    @Column(name = "item_name", nullable = false, length = 25)
    private String itemName;

    @Column(name = "item_description", nullable = false)
    private String description;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="item")
    private Set<Review> reviews;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "image")
    private byte[] image;

    /**
     * Instantiates a new Item.
     *
     * @param itemName    the item name
     * @param description the description
     * @param reviews     the reviews
     * @param price       the price
     * @param image       the image
     */
    public Item(String itemName, String description, Set<Review> reviews, double price, byte[] image) {
        this.itemName = itemName;
        this.description = description;
        this.reviews = reviews;
        this.price = price;
        this.image = image;
    }
}
