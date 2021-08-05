package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
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

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Inventory inventory;

    @Column(name = "item_name", nullable = false, length = 25)
    private String itemName;

    @Column(name = "item_description", nullable = false, length = 255)
    private String description;

    @Column(name = "image", nullable = true)
    private byte[] image;

    /**
     * Instantiates a new Item.
     *
     * @param productName the product name
     * @param description the description
     */
    public Item(String productName, String description) {
        this.itemUUID = UUID.randomUUID();
        this.itemName = productName;
        this.description = description;
        this.image = null;
    }
}
