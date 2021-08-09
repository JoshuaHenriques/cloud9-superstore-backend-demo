package org.jayhenri.ecommerce.model.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jayhenri.ecommerce.model.store.Item;

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
    private UUID orderDetailsUUID = UUID.randomUUID();

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
     * Instantiates a new Order details.
     *
     * @param orderStatus   the order status
     */
    public Orders(String orderStatus, double total) {
        this.orderStatus = orderStatus;
        this.total = total;
    }
}
