package org.jayhenri.cloud9.service.inventory;

import org.jayhenri.cloud9.model.inventory.OnlineInventory;
import org.jayhenri.cloud9.repository.inventory.OnlineInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Inventory service.
 */
@Service
public class OnlineInventoryService {

    private final OnlineInventoryRepository inventoryRepository;

    /**
     * Instantiates a new Inventory service.
     *
     * @param inventoryRepository the inventory repository
     */
    @Autowired
    public OnlineInventoryService(OnlineInventoryRepository inventoryRepository) {

        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Add.
     *
     * @param inventory the inventory
     */
    public void add(OnlineInventory inventory) {

        inventoryRepository.save(inventory);
    }

    /**
     * Update.
     *
     * @param inventory the inventory
     */
    public void update(OnlineInventory inventory) {

        inventoryRepository.save(inventory);
    }

    /**
     * Delete.
     *
     * @param inventory the inventory
     */
    public void delete(OnlineInventory inventory) {

        inventoryRepository.delete(inventory);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<OnlineInventory> findAll() {

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
    public OnlineInventory getByProductName(String productName) {

        return inventoryRepository.getByProductName(productName);
    }
}
