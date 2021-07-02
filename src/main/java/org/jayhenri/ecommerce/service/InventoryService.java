package org.jayhenri.ecommerce.service;

import lombok.NoArgsConstructor;
import org.jayhenri.ecommerce.exception.*;
import org.jayhenri.ecommerce.model.Inventory;
import org.jayhenri.ecommerce.repository.InventoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

// TODO: Implement better
@NoArgsConstructor
@Service
public class InventoryService {

    private Inventory inventory;

    @Autowired
    private InventoryRepository inventoryRepository;

    public void update(Inventory inventory, String productName) throws ItemNotFoundException, InvalidItemException, ProductNameNotSameException {
        if (!ObjectUtils.isEmpty(inventory))
            if (existsByProductName(productName) && existsByProductName(inventory.getProductName()))
                if (productName.equals(inventory.getProductName()))
                    inventoryRepository.save(inventory);
                else throw new ProductNameNotSameException();
            else throw new ItemNotFoundException();
        else throw new InvalidItemException();
    }

    public void add(Inventory inventory) throws ItemAlreadyExistsException, InvalidItemException {
        if (!ObjectUtils.isEmpty(inventory)) {
            if (!existsByProductName(inventory.getProductName())) {
                inventoryRepository.save(inventory);
            } else throw new ItemAlreadyExistsException();
        } else throw new InvalidItemException();

    }

    public void delete(String productName) throws ItemNotFoundException, InvalidItemException {
        Inventory deleteMe;
        if (!ObjectUtils.isEmpty(productName))
            if (existsByProductName(productName)) {
                deleteMe = new Inventory();
                deleteMe.setProductName(productName);
                inventoryRepository.delete(deleteMe);
            }
            else throw new ItemNotFoundException();
        else throw new InvalidItemException();
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
