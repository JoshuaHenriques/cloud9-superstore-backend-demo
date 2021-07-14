package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class OrderDB implements Serializable {
    private static final long serialVersionUID = -5378203026264681312L;

    @Id
    @Column
    private UUID uuid = UUID.randomUUID();

    @Column
    private String orderStatus;

    @Column(unique = true, length = 128, nullable = false)
    private String customerEmail;

    @NotNull
    @OneToMany
    private ArrayList<Item> items;

    @Column
    private double subTotal;

    @Column
    private double totalPrice;

    public OrderDB(String orderStatus, String customerEmail, ArrayList<Item> items, double subTotal, double totalPrice) {
        this.orderStatus = orderStatus;
        this.customerEmail = customerEmail;
        this.items = items;
        this.subTotal = subTotal;
        this.totalPrice = totalPrice;
    }
}
