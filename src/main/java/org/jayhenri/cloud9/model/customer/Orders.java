package org.jayhenri.cloud9.model.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jayhenri.cloud9.model.store.Item;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * The type Order.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = -3543368529566294417L;

    @Id
    @Column(name = "orders_id", nullable = false)
    private UUID ordersUUID = UUID.randomUUID();

    @Column(name = "order_status", nullable = false, length = 10)
    private String orderStatus;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Item> items;

    @Column(name = "total", nullable = false)
    private double total;


    /**
     * Instantiates a new Orders.
     *
     * @param orderStatus the order status
     * @param items       the items
     * @param total       the total
     */
    public Orders(String orderStatus, Set<Item> items, double total) {
        this.orderStatus = orderStatus;
        this.items = items;
        this.total = total;
    }
}
