package org.jayhenri.store_manager.model.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jayhenri.store_manager.model.item.Item;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * The type Orders.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = -3543368529566294417L;

    @Id
    @Column(name = "orders_id", nullable = false)
    private UUID ordersUUID = UUID.randomUUID();

    @Column(name = "order_status", nullable = false, length = 50)
    private String orderStatus;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_items",
            joinColumns =
            @JoinColumn(name = "orders_id"),
            inverseJoinColumns =
            @JoinColumn(name = "item_id")
    )
    private Set<Item> items;

    @ManyToOne
    @JoinColumn(name = "customer_id", unique = true, nullable = false)
    private Customer customer;

    @Column(name = "total", nullable = false)
    private double total;


    /**
     * Instantiates a new Orders.
     *
     * @param orderStatus the order status
     * @param items       the items
     * @param total       the total
     * @param customer    the customer
     */
    public Orders(String orderStatus, Set<Item> items, double total, Customer customer) {
        this.orderStatus = orderStatus;
        this.items = items;
        this.total = total;
        this.customer = customer;
    }
}
