package org.jayhenri.store_manager.service.inventory;

import org.jayhenri.store_manager.exception.OutOfStockException;
import org.jayhenri.store_manager.interfaces.service.other.InventoryServiceI;
import org.jayhenri.store_manager.model.inventory.StoreInventory;
import org.jayhenri.store_manager.repository.inventory.StoreInventoryRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * The type Store inventory service.
 */
@Service
public class StoreInventoryService implements InventoryServiceI<StoreInventory> {

    private final StoreInventoryRepository inventoryRepository;

    /**
     * Instantiates a new Store inventory service.
     *
     * @param inventoryRepository the inventory repository
     */
    @Autowired
    public StoreInventoryService(StoreInventoryRepository inventoryRepository) {

        this.inventoryRepository = inventoryRepository;
    }

    public void add(StoreInventory inventory) {

        inventoryRepository.save(inventory);
    }

    public void update(StoreInventory inventory) {

        inventoryRepository.save(inventory);
    }

    public void delete(StoreInventory inventory) {

        inventoryRepository.delete(inventory);
    }

    public List<StoreInventory> findAll() {

        return inventoryRepository.findAll();
    }

    public boolean existsByItemName(String itemName) {

        return inventoryRepository.existsByItemName(itemName);
    }

    public StoreInventory getByItemName(String itemName) {

        return inventoryRepository.getByItemName(itemName);
    }

    public boolean existsById(UUID uuid) {

        return inventoryRepository.existsById(uuid);
    }

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
