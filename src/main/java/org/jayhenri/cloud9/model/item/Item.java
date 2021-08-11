package org.jayhenri.cloud9.model.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * The type Item.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item implements Serializable {

    private static final long serialVersionUID = -496088096515099704L;

    @Id
    @Column(name = "item_id", nullable = false)
    private UUID itemUUID;

    @Column(name = "item_name", nullable = false, length = 25)
    private String itemName;

    @Column(name = "item_description", nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "item_reviews",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "review_id")
    )
    private Set<Review> reviews;

    @Column(name = "image")
    private byte[] image;

    /**
     * Instantiates a new Item.
     *
     * @param itemName    the item name
     * @param description the description
     * @param reviews     the reviews
     * @param image       the image
     */
    public Item(String itemName, String description, Set<Review> reviews, byte[] image) {
        this.itemName = itemName;
        this.description = description;
        this.reviews = reviews;
        this.image = image;
    }
}
