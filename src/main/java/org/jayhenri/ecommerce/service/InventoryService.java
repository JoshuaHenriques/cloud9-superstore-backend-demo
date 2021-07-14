package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.exception.*;
import org.jayhenri.ecommerce.model.Inventory;
import org.jayhenri.ecommerce.model.Item;
import org.jayhenri.ecommerce.repository.InventoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

// TODO: Implement better
@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public void add(Inventory inventory) {
            inventoryRepository.save(inventory);
    }

    public void update(Inventory inventory) {
            inventoryRepository.save(inventory);
    }

    public void delete(Inventory inventory) {
        inventoryRepository.delete(inventory);
    }

    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    public boolean existsByProductName(String productName) {
        return inventoryRepository.existsByProductName(productName);
    }

    public Inventory getByProductName(String productName) {
        return inventoryRepository.getByProductName(productName);
    }
}
