package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

// TODO: Abstract for different type of items
// TODO: Implement ratings
// TODO: Implement comments
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @Column(unique = true)
    private String productName;



    @Column
    private int quantity;
}
