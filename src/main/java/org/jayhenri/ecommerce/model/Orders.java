package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Orders {

    @Id
    private UUID uuid;

    @Column
    private OrderStatus orderStatus;

    @Column
    private ArrayList<Item> items;

    public Orders(UUID uuid, OrderStatus orderStatus, ArrayList<Item> items) {
        this.uuid = uuid;
        this.orderStatus = orderStatus;
        this.items = items;
    }
}
