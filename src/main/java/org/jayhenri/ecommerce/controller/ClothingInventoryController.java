package org.jayhenri.ecommerce.controller;

import org.jayhenri.ecommerce.exception.*;
import org.jayhenri.ecommerce.model.ClothingInventory;
import org.jayhenri.ecommerce.service.ClothingInventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController // Indicates that the data returned by each method will be written straight into the response body instead of rendering a template
@RequestMapping("api/inventory")
public class ClothingInventoryController {

    @Autowired
    private ClothingInventoryService clothingInventoryService;

    @PutMapping(value = "/updateItem", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClothingInventory> addToStock(@Valid @RequestBody ClothingInventory clothingInventory) throws InvalidItemException, ItemNotFoundException {
        clothingInventoryService.update(clothingInventory);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/addItem", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClothingInventory> addItemToInventory(@Valid @RequestBody ClothingInventory clothingInventory) throws ItemAlreadyExistsException, InvalidItemException {
        clothingInventoryService.add(clothingInventory);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/removeFromStock", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClothingInventory> removeFromStock(@Valid @RequestBody ClothingInventory clothingInventory) throws InvalidItemException, ItemNotFoundException {
        clothingInventoryService.delete(clothingInventory);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/listItems")
    public List<ClothingInventory> findAll() {
        return clothingInventoryService.findAll();
    }
}
