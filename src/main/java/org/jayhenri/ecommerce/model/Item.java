package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// TODO: Implement Ratings
// TODO: There must be a better way to do this
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Item {

    @Column
    private String description;

    @Column
    private double price;
}
