package org.jayhenri.cloud9.service.inventory;

import org.jayhenri.cloud9.model.inventory.StoreInventory;
import org.jayhenri.cloud9.repository.inventory.StoreInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type StoreInventory service.
 */
@Service
public class StoreInventoryService {

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
     * @param inventory the inventory
     */
    public void add(StoreInventory inventory) {

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
     * @param productName the product name
     * @return the boolean
     */
    public boolean existsByProductName(String productName) {

        return inventoryRepository.existsByProductName(productName);
    }

    /**
     * Gets by product name.
     *
     * @param productName the product name
     * @return the by product name
     */
    public StoreInventory getByProductName(String productName) {

        return inventoryRepository.getByProductName(productName);
    }
}
