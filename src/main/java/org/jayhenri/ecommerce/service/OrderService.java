package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.model.Item;
import org.jayhenri.ecommerce.model.Order;

import java.util.ArrayList;

public class OrderService {

    private Order order;

    public void addItemsToOrder(ArrayList<Item> items) {
        order.setOrder(items);
    }

    public void setTotal(){}

    public void setOrderStatus(Order order, String status) {
        order.setOrderStatus(status);
    }
}
