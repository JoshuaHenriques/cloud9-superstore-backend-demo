package org.jayhenri.cloud9.model.customer;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.jayhenri.cloud9.model.item.Item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Order.
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

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(
        name="order_items",
        joinColumns=
            @JoinColumn(name="orders_id"),
        inverseJoinColumns=
            @JoinColumn(name="item_id")
    )
    private Set<Item> items;

    @ManyToOne
    @JoinColumn(name="customer_id", unique=true, nullable=false)
    private Customer customer;

    @Column(name = "total", nullable = false)
    private double total;


    /**
     * Instantiates a new Orders.
     *
     * @param orderStatus the order status
     * @param items       the items
     * @param total       the total
     */
    public Orders(String orderStatus, Set<Item> items, double total, Customer customer) {
        this.orderStatus = orderStatus;
        this.items = items;
        this.total = total;
        this.customer = customer;
    }
}
