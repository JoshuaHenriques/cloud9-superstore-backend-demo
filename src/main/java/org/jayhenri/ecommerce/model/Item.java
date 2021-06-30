package org.jayhenri.ecommerce.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    @Id
    private UUID uuid;

    @Column
    private String name;

//    @NotNull
//    @Column
//    private Rating rating; // TODO: Implement later


    @Column
    private double price;

//    @NotNull
//    @Column
//    private char tag; // TODO: Implement later

    public Item(String name, double price) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.price = price;
    }
}
