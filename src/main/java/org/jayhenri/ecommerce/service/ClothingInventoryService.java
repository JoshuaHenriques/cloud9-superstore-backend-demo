package org.jayhenri.ecommerce.service;

import lombok.NoArgsConstructor;
import org.jayhenri.ecommerce.exception.*;
import org.jayhenri.ecommerce.model.ClothingInventory;
import org.jayhenri.ecommerce.model.Customer;
import org.jayhenri.ecommerce.repository.ClothingInventoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

// TODO: Implement better
@NoArgsConstructor
@Service
public class ClothingInventoryService {

    private ClothingInventory clothingInventory;

    @Autowired
    private ClothingInventoryRepository clothingInventoryRepository;

    public void update(ClothingInventory clothingInventory, String productName) throws ItemNotFoundException, InvalidItemException, ProductNameNotSameException {
        if (!ObjectUtils.isEmpty(clothingInventory))
            if (existsByProductName(productName) && existsByProductName(clothingInventory.getProductName()))
                if (productName.equals(clothingInventory.getProductName()))
                    clothingInventoryRepository.save(clothingInventory);
                else throw new ProductNameNotSameException();
            else throw new ItemNotFoundException();
        else throw new InvalidItemException();
    }

    public void add(ClothingInventory clothingInventory) throws ItemAlreadyExistsException, InvalidItemException {
        if (!ObjectUtils.isEmpty(clothingInventory)) {
            if (!existsByProductName(clothingInventory.getProductName())) {
                clothingInventoryRepository.save(clothingInventory);
            } else throw new ItemAlreadyExistsException();
        } else throw new InvalidItemException();

    }

    public void delete(String productName) throws ItemNotFoundException, InvalidItemException {
        ClothingInventory deleteMe;
        if (!ObjectUtils.isEmpty(productName))
            if (existsByProductName(productName)) {
                deleteMe = new ClothingInventory();
                deleteMe.setProductName(productName);
                clothingInventoryRepository.delete(deleteMe);
            }
            else throw new ItemNotFoundException();
        else throw new InvalidItemException();
    }

    public List<ClothingInventory> findAll() {
        return clothingInventoryRepository.findAll();
    }

    public boolean existsByProductName(String productName) {
        return clothingInventoryRepository.existsByProductName(productName);
    }

    public ClothingInventory getByProductName(String productName) {
        return clothingInventoryRepository.getByProductName(productName);
    }
}
