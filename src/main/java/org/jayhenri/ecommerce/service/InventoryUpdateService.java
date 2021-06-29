package org.jayhenri.ecommerce.service;

import org.javatuples.Quartet;
import org.jayhenri.ecommerce.exception.OutOfStockException;
import org.jayhenri.ecommerce.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

// TODO: Implement better
@Service
public class InventoryUpdateService {

    private Inventory inventory;

    public InventoryUpdateService() {
        inventory = new Inventory();
    }

    public void addToStock(UUID uuid, int s, int m, int l) {
        Integer getAt1 = inventory.getItems().get(inventory.getItems().indexOf(uuid)).getValue1();
        Integer getAt2 = inventory.getItems().get(inventory.getItems().indexOf(uuid)).getValue2();
        Integer getAt3 = inventory.getItems().get(inventory.getItems().indexOf(uuid)).getValue3();

        inventory.getItems().get(inventory.getItems().indexOf(uuid)).setAt1(getAt1 + s);
        inventory.getItems().get(inventory.getItems().indexOf(uuid)).setAt1(getAt2 + m);
        inventory.getItems().get(inventory.getItems().indexOf(uuid)).setAt1(getAt3 + l);
    }

    public void removeToStock(UUID uuid, int s, int m, int l) throws OutOfStockException {

        Integer getAt1 = inventory.getItems().get(inventory.getItems().indexOf(uuid)).getValue1();
        Integer getAt2 = inventory.getItems().get(inventory.getItems().indexOf(uuid)).getValue2();
        Integer getAt3 = inventory.getItems().get(inventory.getItems().indexOf(uuid)).getValue3();

        if (checkInventory(uuid, s,m,l)) {
            inventory.getItems().get(inventory.getItems().indexOf(uuid)).setAt1(getAt1 - s);
            inventory.getItems().get(inventory.getItems().indexOf(uuid)).setAt1(getAt2 - m);
            inventory.getItems().get(inventory.getItems().indexOf(uuid)).setAt1(getAt3 - l);
        } else throw new OutOfStockException();
    }

    public void addItemToInventory(UUID uuid, int s, int m, int l) {

        inventory.getItems().add(new Quartet<>(uuid, s, m, l));
    }

    public void removeItemFromInventory(UUID uuid) throws OutOfStockException {

        inventory.getItems().remove(inventory.getItems().indexOf(uuid));
    }

    public boolean checkInventory(UUID uuid, int s, int m, int l) {

        Integer getAt1 = inventory.getItems().get(inventory.getItems().indexOf(uuid)).getValue1();
        Integer getAt2 = inventory.getItems().get(inventory.getItems().indexOf(uuid)).getValue2();
        Integer getAt3 = inventory.getItems().get(inventory.getItems().indexOf(uuid)).getValue3();

        if ((getAt1 - s) > 0 && (getAt2 - m) > 0 && (getAt3 - l) > 0) {
            return true;
        } else return false;
    }

    public int count() {
        return 0;
    }
}
