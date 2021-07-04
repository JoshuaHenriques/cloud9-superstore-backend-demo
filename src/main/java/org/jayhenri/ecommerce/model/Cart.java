package org.jayhenri.ecommerce.model;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Embeddable;

// TODO: Implement HST+GST+PROMOCODE computation

@Getter
@Setter
@Embeddable
public class Cart implements Serializable {

    private static final long serialVersionUID = -198235381052492730L;

    private ArrayList<Item> items;

    private String customerEmail;

    private Double total;

}