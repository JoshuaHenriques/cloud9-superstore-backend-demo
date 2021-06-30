package org.jayhenri.ecommerce.service;

import lombok.NoArgsConstructor;
import org.jayhenri.ecommerce.exception.InvalidItemException;
import org.jayhenri.ecommerce.exception.ItemAlreadyExistsException;
import org.jayhenri.ecommerce.exception.ItemNotFoundException;
import org.jayhenri.ecommerce.model.ClothingInventory;
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

    public void update(ClothingInventory clothingInventory) throws ItemNotFoundException, InvalidItemException {
            if (!ObjectUtils.isEmpty(clothingInventory)) {
                if (!existsByProductName(clothingInventory)) {
                    throw new ItemNotFoundException("Cannot find Item: " + clothingInventory.getProductName());
                }
                clothingInventoryRepository.save(clothingInventory);
            } else {
                InvalidItemException exc = new InvalidItemException("Failed to add item");
                exc.addErrorMessage("Item given is null or empty");
                throw exc;
            }
    }

    public void add(ClothingInventory clothingInventory) throws ItemAlreadyExistsException, InvalidItemException {
        if (!ObjectUtils.isEmpty(clothingInventory)) {
            if (!existsByProductName(clothingInventory)) {
                clothingInventoryRepository.save(clothingInventory);
            }
            throw new ItemAlreadyExistsException();
        } else {
            throw new InvalidItemException();
        }
    }

    public void delete(ClothingInventory clothingInventory) throws ItemNotFoundException, InvalidItemException {
        if (!ObjectUtils.isEmpty(clothingInventory)) {
            if (existsByProductName(clothingInventory)) {
                clothingInventoryRepository.delete(clothingInventory);
            }
            throw new ItemNotFoundException();
        } else {
            throw new InvalidItemException();
        }
    }

    public List<ClothingInventory> findAll() {
        return clothingInventoryRepository.findAll();
    }

    public boolean existsByProductName(ClothingInventory clothingInventory) {
        return clothingInventoryRepository.existsByProductName(clothingInventory.getProductName());
    }
}
