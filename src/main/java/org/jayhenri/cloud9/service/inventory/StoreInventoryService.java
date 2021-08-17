package org.jayhenri.cloud9.service.inventory;

import org.jayhenri.cloud9.exception.OutOfStockException;
import org.jayhenri.cloud9.interfaces.service.other.InventoryServiceI;
import org.jayhenri.cloud9.model.inventory.OnlineInventory;
import org.jayhenri.cloud9.model.inventory.StoreInventory;
import org.jayhenri.cloud9.model.item.Item;
import org.jayhenri.cloud9.repository.inventory.StoreInventoryRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * The type Inventory service.
 */
@Service
public class StoreInventoryService implements InventoryServiceI<StoreInventory> {

    private final StoreInventoryRepository inventoryRepository;

    /**
     * Instantiates a new Inventory service.
     *
     * @param inventoryRepository the inventory repository
     */
    @Autowired
    public StoreInventoryService(StoreInventoryRepository inventoryRepository) {

        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Add.
     *
     * @param item     the item
     * @param quantity the quantity
     * @param price    the price
     */
    public void add(Item item, int quantity, double price) {

        StoreInventory inventory = new StoreInventory(item, item.getItemName(), quantity, price);
        inventoryRepository.save(inventory);
    }

    /**
     * Update.
     *
     * @param inventory the inventory
     */
    public void update(StoreInventory inventory) {

        inventoryRepository.save(inventory);
    }

    /**
     * Delete.
     *
     * @param inventory the inventory
     */
    public void delete(StoreInventory inventory) {

        inventoryRepository.delete(inventory);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<StoreInventory> findAll() {

        return inventoryRepository.findAll();
    }

    /**
     * Exists by product name boolean.
     *
     * @param itemName the item name
     * @return the boolean
     */
    public boolean existsByItemName(String itemName) {

        return inventoryRepository.existsByItemName(itemName);
    }

    /**
     * Gets by product name.
     *
     * @param itemName the item name
     * @return the by product name
     */
    public StoreInventory getByItemName(String itemName) {

        return inventoryRepository.getByItemName(itemName);
    }

    /**
     * Exists by email boolean.
     *
     * @param uuid the email
     * @return the boolean
     */
    public boolean existsById(UUID uuid) {

        return inventoryRepository.existsById(uuid);
    }

    /**
     * Gets by email.
     *
     * @param uuid the email
     * @return the by email
     */
    public StoreInventory getById(UUID uuid) {

        return inventoryRepository.getById(uuid);
    }

    public boolean canPurchase(@NotNull StoreInventory storeInventory, int quantity) {
        return (storeInventory.getQuantity() - quantity) >= 0;
    }

    public void purchase(StoreInventory storeInventory, int quantity) throws OutOfStockException {
        if (canPurchase(storeInventory, quantity))
            storeInventory.setQuantity(storeInventory.getQuantity() - quantity);
        else throw new OutOfStockException();
    }
}
