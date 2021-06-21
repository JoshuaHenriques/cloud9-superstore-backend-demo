package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long UUID;

    @Column
    private OrderStatus orderStatus;

    @Column
    private ArrayList<Item> items;
}
