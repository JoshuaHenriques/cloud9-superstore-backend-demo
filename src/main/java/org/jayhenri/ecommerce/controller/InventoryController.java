package org.jayhenri.ecommerce.controller;

import org.jayhenri.ecommerce.exception.*;
import org.jayhenri.ecommerce.model.Inventory;
import org.jayhenri.ecommerce.service.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The type Inventory controller.
 */
@RestController // Indicates that the data returned by each method will be written straight into the response body instead of rendering a template
@RequestMapping("api/inventory")
public class InventoryController {


    private final InventoryService inventoryService;

    /**
     * Instantiates a new Inventory controller.
     *
     * @param inventoryService the inventory service
     */
    @Autowired
    public InventoryController(InventoryService inventoryService) {

        this.inventoryService = inventoryService;
    }

    /**
     * Update item response entity.
     *
     * @param inventory the inventory
     * @return the response entity
     * @throws InvalidItemException        the invalid item exception
     * @throws ItemAlreadyExistsException  the item already exists exception
     * @throws ItemNotFoundException
     */
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateItem(@Valid @RequestBody Inventory inventory) throws InvalidItemException, ItemNotFoundException {
        if (!ObjectUtils.isEmpty(inventory)) {
            if (inventoryService.existsByProductName(inventory.getProductName())) {
                inventoryService.update(inventory);
                return ResponseEntity.ok().build();
            } else throw new ItemNotFoundException();
        } else throw new InvalidItemException();
    }

    /**
     * Add item to inventory response entity.
     *
     * @param inventory the inventory
     * @return the response entity
     * @throws ItemAlreadyExistsException the item already exists exception
     * @throws InvalidItemException       the invalid item exception
     */
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Inventory> addItem(@Valid @RequestBody Inventory inventory) throws ItemAlreadyExistsException, InvalidItemException {
        if (!ObjectUtils.isEmpty(inventory)) {
            if (!inventoryService.existsByProductName(inventory.getProductName())) {
                inventoryService.add(inventory);
            } else throw new ItemAlreadyExistsException();
        } else throw new InvalidItemException();
        return ResponseEntity.ok().build();
    }

    /**
     * Gets by product name.
     *
     * @param productName the product name
     * @return the by product name
     * @throws ItemNotFoundException the item not found exception
     */
    @GetMapping(value = "/get/{productName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Inventory getByProductName(@Valid @PathVariable String productName) throws ItemNotFoundException {
        if (inventoryService.existsByProductName(productName)) {
            return inventoryService.getByProductName(productName);
        } else throw new ItemNotFoundException();
    }

    /**
     * Remove item to inventory response entity.
     *
     * @param productName the product name
     * @return the response entity
     * @throws InvalidItemException  the invalid item exception
     * @throws ItemNotFoundException the item not found exception
     */
    @DeleteMapping(value = "/remove/{productName}")
    public ResponseEntity<Inventory> removeItem(@PathVariable String productName) throws InvalidItemException, ItemNotFoundException {
        if (!ObjectUtils.isEmpty(productName)) {
            if (inventoryService.existsByProductName(productName)) {
                inventoryService.delete(inventoryService.getByProductName(productName));
            } else throw new ItemNotFoundException();
        } else throw new InvalidItemException();

        return ResponseEntity.ok().build();
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    @GetMapping(value = "/items/list")
    public List<Inventory> findAll() {

        return inventoryService.findAll();
    }
}
