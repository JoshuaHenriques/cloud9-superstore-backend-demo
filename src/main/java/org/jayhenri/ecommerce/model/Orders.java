package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class Orders {

    @Id
    private UUID uuid;

    // TODO: Fix enum import
    @Column
    private String orderStatus;

    @Column
    private ArrayList<Item> orders;

    // TODO: Implement addItemsToOrder()
    public Orders() {
        orderStatus = "PREPARING";
        this.orders = new ArrayList<Item>();
    }
}
