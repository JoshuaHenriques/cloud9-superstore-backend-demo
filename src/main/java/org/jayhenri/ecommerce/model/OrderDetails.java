package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * The type Order.
 */
// TODO: Fix enum import
@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderDetails extends AuditModel implements Serializable {

    private static final long serialVersionUID = -3543368529566294417L;

    @Id
    @Column(nullable = false)
    private UUID orderUUID = UUID.randomUUID();

    @Column(nullable = false)
    private String orderStatus;

    @Column(nullable = false)
    private String customerEmail;

    @JoinColumn(name = "itemUUID", nullable = false, insertable=false, updatable=false)
    @OneToMany
    private List<Item> itemList;

    @Column(nullable = false)
    private double totalPrice;

    public OrderDetails(String orderStatus, String customerEmail, List<Item> itemList, double totalPrice) {
        this.orderStatus = orderStatus;
        this.customerEmail = customerEmail;
        this.itemList = itemList;
        this.totalPrice = totalPrice;
    }
}
