package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private UUID uuid;

    // TODO: Fix enum import
    // @Column
    // private OrderStatus orderStatus;

    @Column
    private ArrayList<UUID> items;

    // TODO: Implement addItemsToOrder()
    public Orders(UUID uuid) {
        this.uuid = uuid;
        this.items = new ArrayList<UUID>();
    }
}
