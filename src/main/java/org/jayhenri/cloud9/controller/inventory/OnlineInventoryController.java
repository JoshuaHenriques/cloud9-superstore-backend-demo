package org.jayhenri.cloud9.controller.onlineInventory;

import org.jayhenri.cloud9.exception.alreadyexists.ItemAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidItemException;
import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
import org.jayhenri.cloud9.model.inventory.OnlineInventory;
import org.jayhenri.cloud9.model.item.Item;
import org.jayhenri.cloud9.service.inventory.OnlineInventoryService;
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
 * The type OnlineInventory controller.
 */
@RestController // Indicates that the data returned by each method will be written straight into
// the response body instead of rendering a template
@RequestMapping("api/onlineInventory")
public class OnlineInventoryController {

    private final OnlineInventoryService onlineInventoryService;

    /**
     * Instantiates a new OnlineInventory controller.
     *
     * @param onlineInventoryService the onlineInventory service
     */
    @Autowired
    public OnlineInventoryController(OnlineInventoryService onlineInventoryService) {

        this.onlineInventoryService = onlineInventoryService;
    }

    /**
     * Add item to onlineInventory response entity.
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
    public ResponseEntity<String> addItem(@RequestBody Item item, @PathVariable UUID itemId, @RequestBody int quantity, @RequestBody double price)
            throws ItemAlreadyExistsException, InvalidItemException {
        if (!ObjectUtils.isEmpty(item)) {
            if (!onlineInventoryService.existsByItemName(item.getItemName())) {
                item.setItemUUID(itemId);
                onlineInventoryService.add(item, quantity, price);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("OnlineInventoryController", "addItem");
                return new ResponseEntity<>("Successfully Created Item", responseHeaders, HttpStatus.CREATED);
            } else
                throw new ItemAlreadyExistsException();
        } else
            throw new InvalidItemException();
    }

    /**
     * Update item response entity.
     *
     * @param onlineInventory the onlineInventory
     * @return the response entity
     * @throws InvalidItemException  the invalid item exception
     * @throws ItemNotFoundException the item not found exception
     */
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateItem(@RequestBody OnlineInventory onlineInventory)
            throws InvalidItemException, ItemNotFoundException {
        if (!ObjectUtils.isEmpty(onlineInventory)) {
            if (onlineInventoryService.existsByItemName(onlineInventory.getItemName())) {
                onlineInventoryService.update(onlineInventory);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("OnlineInventoryController", "updateItem");
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
    public ResponseEntity<OnlineInventory> getByItemName(@PathVariable String itemName) throws ItemNotFoundException {
        if (onlineInventoryService.existsByItemName(itemName)) {

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("OnlineInventoryController", "getByItemName");
            OnlineInventory onlineInventory = onlineInventoryService.getByItemName(itemName);
            return new ResponseEntity<>(onlineInventory, responseHeaders, HttpStatus.OK);
        } else
            throw new ItemNotFoundException();
    }

    /**
     * Remove item to onlineInventory response entity.
     *
     * @param itemName the item name
     * @return the response entity
     * @throws InvalidItemException  the invalid item exception
     * @throws ItemNotFoundException the item not found exception
     */
    @DeleteMapping(value = "/remove/{itemName}")
    public ResponseEntity<String> removeItem(@PathVariable String itemName)
            throws InvalidItemException, ItemNotFoundException {
        if (!ObjectUtils.isEmpty(itemName)) {
            if (onlineInventoryService.existsByItemName(itemName)) {
                onlineInventoryService.delete(onlineInventoryService.getByItemName(itemName));

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("OnlineInventoryController", "removeItem");
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
    @GetMapping(value = "/items/list")
    public ResponseEntity<List<OnlineInventory>> findAll() {

        List<OnlineInventory> allInventories = onlineInventoryService.findAll();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("OnlineInventoryController", "findAll");
        return new ResponseEntity<>(allInventories, responseHeaders, HttpStatus.OK);
    }
}
