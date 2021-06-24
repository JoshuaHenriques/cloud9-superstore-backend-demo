package org.jayhenri.ecommerce.service;

import org.javatuples.Quartet;
import org.jayhenri.ecommerce.exception.OutOfStockException;
import org.jayhenri.ecommerce.model.Inventory;
import org.jayhenri.ecommerce.model.Item;
import org.jayhenri.ecommerce.model.OldInventory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class InventoryUpdateService {

    @Autowired
    private Inventory inventory;

    public InventoryUpdateService() {
        inventory = new Inventory();
    }

    public void addToInventory(UUID uuid, int s, int m, int l) {

        inventory.getItems().add(new Quartet<>(uuid, s, m, l));
    }

    public void removeFromInventory(UUID uuid) throws OutOfStockException {

        inventory.getItems().remove(inventory.getItems().indexOf(uuid));
    }

    // TODO
    public boolean checkInventory(UUID uuid, int amount) {
        if ((inventory.getItems()))
        return false;
    }

    public void listInventory() {}

    public int count() {

        return inventory.getProdACount() +
                inventory.getProdBCount() +
                inventory.getProdCCount() +
                inventory.getProdECount() +
                inventory.getProdDCount();
    }
}
