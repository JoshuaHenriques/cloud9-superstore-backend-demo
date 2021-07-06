package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

// TODO: Implement Ratings
// TODO: There must be a better way to do this
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Item implements Serializable {

    private static final long serialVersionUID = -496088096515099704L;

    private String itemName;

    private String description;

    private double price;

    private byte[] image;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "Create_Date", nullable = false)
//    private Date createDate;

    public Item(String productName, String description, double price) {
        this.itemName = productName;
        this.description = description;
        this.price = price;
        this.image = null;
    }
}
