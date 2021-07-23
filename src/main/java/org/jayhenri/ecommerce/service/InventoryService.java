package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.exception.*;
import org.jayhenri.ecommerce.model.Inventory;
import org.jayhenri.ecommerce.model.Item;
import org.jayhenri.ecommerce.repository.InventoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * The type Inventory service.
 */
@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    /**
     * Instantiates a new Inventory service.
     *
     * @param inventoryRepository the inventory repository
     */
    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {

        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Add.
     *
     * @param inventory the inventory
     */
    public void add(Inventory inventory) {

            inventoryRepository.save(inventory);
    }

    /**
     * Update.
     *
     * @param inventory the inventory
     */
    public void update(Inventory inventory) {

            inventoryRepository.save(inventory);
    }

    /**
     * Delete.
     *
     * @param inventory the inventory
     */
    public void delete(Inventory inventory) {

        inventoryRepository.delete(inventory);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Inventory> findAll() {

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
    public Inventory getByProductName(String productName) {

        return inventoryRepository.getByProductName(productName);
    }
}
