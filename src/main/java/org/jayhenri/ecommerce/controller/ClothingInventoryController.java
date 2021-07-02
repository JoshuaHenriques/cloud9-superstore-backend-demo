package org.jayhenri.ecommerce.controller;

import org.jayhenri.ecommerce.exception.*;
import org.jayhenri.ecommerce.model.Inventory;
import org.jayhenri.ecommerce.service.InventoryService;

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
    private InventoryService inventoryService;

    @PutMapping(value = "/updateItem/{productName}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Inventory> updateItem(@Valid @RequestBody Inventory inventory, @PathVariable String productName) throws InvalidItemException, ItemNotFoundException, ProductNameNotSameException {
        inventoryService.update(inventory, productName);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/addItem", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Inventory> addItemToInventory(@Valid @RequestBody Inventory inventory) throws ItemAlreadyExistsException, InvalidItemException {
        inventoryService.add(inventory);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/getByName/{productName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Inventory getByProductName(@Valid @PathVariable String productName) {
        return inventoryService.getByProductName(productName);
    }

    @PostMapping(value = "/removeFromStock/{productName}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Inventory> removeItemToInventory(@Valid @RequestBody Inventory inventory, @PathVariable String productName) throws InvalidItemException, ItemNotFoundException {
        inventoryService.delete(productName);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/listItems")
    public List<Inventory> findAll() {
        return inventoryService.findAll();
    }
}
