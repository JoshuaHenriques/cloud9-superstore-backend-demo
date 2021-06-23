package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.exception.OutOfStockException;
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

    public void removeFromInventory(char prod, int amount) throws OutOfStockException {

        switch (prod) {
            case 'a': {
                if (checkInventory(prod, amount)) inventory.subProdACount(amount);
                else throw new OutOfStockException();
            }
            case 'b': {
                checkInventory(prod, amount);
                inventory.subProdBCount(amount);
            }
            case 'c': {
                checkInventory(prod, amount);
                inventory.subProdCCount(amount);
            }
            case 'd': {
                checkInventory(prod, amount);
                inventory.subProdDCount(amount);
            }
            case 'e': {
                checkInventory(prod, amount);
                inventory.subProdECount(amount);
            }
        }
    }

    public boolean checkInventory(char prod, int amount) {
        return false;
    }

    public int count() {

        return inventory.getProdACount() +
                inventory.getProdBCount() +
                inventory.getProdCCount() +
                inventory.getProdECount() +
                inventory.getProdDCount();
    }
}
