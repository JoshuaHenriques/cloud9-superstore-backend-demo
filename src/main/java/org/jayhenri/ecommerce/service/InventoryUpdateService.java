package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.model.Inventory;

public class InventoryUpdateService {

    private Inventory inventory;

    public InventoryUpdateService() {
        inventory = new Inventory();
    }

    public void addToInventory(char prod, int amount) {

        switch (prod) {
            case 'a': inventory.addProdACount(amount);
            case 'b': inventory.addProdBCount(amount);
            case 'c': inventory.addProdCCount(amount);
            case 'd': inventory.addProdDCount(amount);
            case 'e': inventory.addProdECount(amount);
        }
    }

    public void removeFromInventory(char prod, int amount) {

        switch (prod) {
            case 'a': inventory.subProdACount(amount);
            case 'b': inventory.subProdBCount(amount);
            case 'c': inventory.subProdCCount(amount);
            case 'd': inventory.subProdDCount(amount);
            case 'e': inventory.subProdECount(amount);
        }
    }

    public void checkInventory(char prod, int amount) {

    }

    public int count() {

        return inventory.getProdACount() +
                inventory.getProdBCount() +
                inventory.getProdCCount() +
                inventory.getProdECount() +
                inventory.getProdDCount();
    }
}
