package org.jayhenri.cloud9.controller.inventory;

import org.jayhenri.cloud9.exception.alreadyexists.ItemAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidItemException;
import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
import org.jayhenri.cloud9.interfaces.controller.other.InventoryControllerI;
import org.jayhenri.cloud9.interfaces.service.other.InventoryServiceI;
import org.jayhenri.cloud9.model.inventory.StoreInventory;
import org.jayhenri.cloud9.model.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * The type StoreInventory controller.
 */
@RestController // Indicates that the data returned by each method will be written straight into
// the response body instead of rendering a template
@RequestMapping("api/inventory/store")
public class StoreInventoryController implements InventoryControllerI<StoreInventory> {

    private final InventoryServiceI<StoreInventory> storeInventoryService;

    /**
     * Instantiates a new StoreInventory controller.
     *
     * @param storeInventoryService the storeInventory service
     */
    @Autowired
    public StoreInventoryController(InventoryServiceI<StoreInventory> storeInventoryService) {

        this.storeInventoryService = storeInventoryService;
    }

    /**
     * Add item to storeInventory response entity.
     *
     * @param item     the item
     * @param itemId   the item id
     * @param quantity the quantity
     * @param price    the price
     * @return the response entity
     * @throws ItemAlreadyExistsException the item already exists exception
     * @throws InvalidItemException       the invalid item exception
     */
    @PostMapping(value = "/add/{itemId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@RequestBody Item item, @PathVariable UUID itemId, @RequestBody int quantity, @RequestBody double price)
            throws ItemAlreadyExistsException, InvalidItemException {
        if (!ObjectUtils.isEmpty(item)) {
            if (!storeInventoryService.existsByItemName(item.getItemName())) {
                item.setItemUUID(itemId);
                storeInventoryService.add(item, quantity, price);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("StoreInventoryController", "add");
                return new ResponseEntity<>("Successfully Created Item", responseHeaders, HttpStatus.CREATED);
            } else
                throw new ItemAlreadyExistsException();
        } else
            throw new InvalidItemException();
    }

    /**
     * Update item response entity.
     *
     * @param storeInventory the storeInventory
     * @return the response entity
     * @throws InvalidItemException  the invalid item exception
     * @throws ItemNotFoundException the item not found exception
     */
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody StoreInventory storeInventory)
            throws InvalidItemException, ItemNotFoundException {
        if (!ObjectUtils.isEmpty(storeInventory)) {
            if (storeInventoryService.existsByItemName(storeInventory.getItemName())) {
                storeInventoryService.update(storeInventory);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("StoreInventoryController", "update");
                return new ResponseEntity<>("Successfully Updated Item", responseHeaders, HttpStatus.OK);
            } else
                throw new ItemNotFoundException();
        } else
            throw new InvalidItemException();
    }

    /**
     * Gets by product name.
     *
     * @param itemName the item name
     * @return the by product name
     * @throws ItemNotFoundException the item not found exception
     */

    @GetMapping(value = "/get/{itemName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreInventory> getByItemName(@PathVariable String itemName) throws ItemNotFoundException {
        if (storeInventoryService.existsByItemName(itemName)) {

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("OnlineInventoryController", "getByItemName");
            StoreInventory storeInventory = storeInventoryService.getByItemName(itemName);
            return new ResponseEntity<>(storeInventory, responseHeaders, HttpStatus.OK);
        } else
            throw new ItemNotFoundException();
    }

    /**
     * Get response entity.
     *
     * @param itemId the item name
     * @return the response entity
     * @throws ItemNotFoundException the item not found exception
     */
    @GetMapping(value = "/get/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreInventory> getById(@PathVariable UUID itemId) throws ItemNotFoundException {
        if (storeInventoryService.existsById(itemId)) {

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("OnlineInventoryController", "getByItemId");
            StoreInventory storeInventory = storeInventoryService.getById(itemId);
            return new ResponseEntity<>(storeInventory, responseHeaders, HttpStatus.OK);
        } else
            throw new ItemNotFoundException();
    }

    /**
     * Remove item to storeInventory response entity.
     *
     * @param itemName the item name
     * @return the response entity
     * @throws InvalidItemException  the invalid item exception
     * @throws ItemNotFoundException the item not found exception
     */
    @DeleteMapping(value = "/remove/{itemName}")
    public ResponseEntity<String> remove(@PathVariable String itemName)
            throws InvalidItemException, ItemNotFoundException {
        if (!ObjectUtils.isEmpty(itemName)) {
            if (storeInventoryService.existsByItemName(itemName)) {
                storeInventoryService.delete(storeInventoryService.getByItemName(itemName));

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("StoreInventoryController", "remove");
                return new ResponseEntity<>("Successfully Deleted Item", responseHeaders, HttpStatus.OK);
            } else
                throw new ItemNotFoundException();
        } else
            throw new InvalidItemException();
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    @GetMapping(value = "/list")
    public ResponseEntity<List<StoreInventory>> findAll() {

        List<StoreInventory> allInventories = storeInventoryService.findAll();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("StoreInventoryController", "findAll");
        return new ResponseEntity<>(allInventories, responseHeaders, HttpStatus.OK);
    }
}
