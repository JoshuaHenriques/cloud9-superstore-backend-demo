package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

// TODO: Fix enum import
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Order implements Serializable {

    @Column
    private String orderStatus;

    @Column(unique = true, length = 128, nullable = false)
    private String CustomerEmail;

    @Column
    private ArrayList<Item> order;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Create_Date", nullable = false)
    private Date date;

    @Column
    private double subTotal;

    @Column
    private double totalPrice;

}
