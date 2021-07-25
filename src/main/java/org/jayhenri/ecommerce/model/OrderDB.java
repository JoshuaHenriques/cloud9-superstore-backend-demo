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
@Table(name = "orderDetails")
public class OrderDB implements Serializable {
    private static final long serialVersionUID = -5378203026264681312L;

    @Id
    @Column(nullable = false)
    private UUID orderDBUUID = UUID.randomUUID();

    @Column(nullable = false)
    private String orderStatus;

    @Column(unique = true, length = 128, nullable = false)
    private String customerEmail;

    @JoinColumn(name = "itemUUID", nullable = false, insertable = false, updatable = false)
    @OneToMany
    private List<Item> items;

    @Column(nullable = false)
    private double subTotal;

    @Column(nullable = false)
    private double totalPrice;

    /**
     * Instantiates a new Order db.
     *
     * @param orderStatus   the order status
     * @param customerEmail the customer email
     * @param items         the items
     * @param subTotal      the sub total
     * @param totalPrice    the total price
     */
    public OrderDB(String orderStatus, String customerEmail, List<Item> items, double subTotal, double totalPrice) {
        this.orderStatus = orderStatus;
        this.customerEmail = customerEmail;
        this.items = items;
        this.subTotal = subTotal;
        this.totalPrice = totalPrice;
    }
}
