package org.jayhenri.ecommerce.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @Column
    private UUID uuid;

    @NotNull
    @Column
    private String name;

//    @NotNull
//    @Column
//    private Rating rating; // TODO: Implement later

    @NotNull
    @Column
    private double price;

    @NotNull
    @Column
    private char tag; // TODO: Implement later

    public Item(UUID uuid, String name, double price, char tag) {
        this.uuid = uuid;
        this.name = name;
        this.price = price;
        this.tag = tag;
    }
}
