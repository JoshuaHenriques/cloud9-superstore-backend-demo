package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * The type Order db.
 * todo: merge with order
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "orderDBDetails")
public class OrderDB implements Serializable {
    private static final long serialVersionUID = -5378203026264681312L;
    private static final double HST = 0.13;
    private static final double DELIVERY = 9.99;
    @Id
    @Column(nullable = false)
    private UUID orderDBUUID;
    @Column(nullable = false)
    private String orderStatus;
    @Column(unique = true, length = 128, nullable = false)
    private String customerEmail;
    @JoinColumn(name = "itemUUID", nullable = true, insertable = false, updatable = false)
    @OneToMany
    private List<Item> items;
    @Column(nullable = false)
    private double subTotal;
    @Column(nullable = false)
    private double totalPrice;

    /**
     * Instantiates a new Order db.
     *
     * @param orderDetails the order orderDetails
     */
    public OrderDB(OrderDetails orderDetails) {
        this.orderDBUUID = orderDetails.getOrderUUID();
        this.orderStatus = orderDetails.getOrderStatus();
        this.customerEmail = orderDetails.getCustomerEmail();
        this.items = orderDetails.getItemList();
        this.subTotal = orderDetails.getTotalPrice();
        this.totalPrice = orderDetails.getTotalPrice() * HST * DELIVERY;
    }
}
