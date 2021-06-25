package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.UUID;

// import org.jayhenri.ecommerce.model.OrderStatus;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    private UUID uuid;
    // TODO: Fix enum import
    // @Column
    // private OrderStatus orderStatus;

    @Column
    private ArrayList<Item> items;

    public Orders(UUID uuid, ArrayList<Item> items) {
        this.uuid = uuid;
        this.items = items;
    }
}
