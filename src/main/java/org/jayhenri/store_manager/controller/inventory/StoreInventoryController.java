package org.jayhenri.store_manager.controller.inventory;

import org.jayhenri.store_manager.exception.alreadyexists.InventoryAlreadyExistsException;
import org.jayhenri.store_manager.exception.invalid.InvalidInventoryException;
import org.jayhenri.store_manager.exception.invalid.InvalidItemException;
import org.jayhenri.store_manager.exception.notfound.ItemNotFoundException;
import org.jayhenri.store_manager.interfaces.controller.other.InventoryControllerI;
import org.jayhenri.store_manager.interfaces.service.other.InventoryServiceI;
import org.jayhenri.store_manager.interfaces.service.other.ItemServiceI;
import org.jayhenri.store_manager.model.inventory.StoreInventory;
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
 * The type Store inventory controller.
 */
@RestController // Indicates that the data returned by each method will be written straight into
// the response body instead of rendering a template
@RequestMapping("api/inventory/store")
public class StoreInventoryController implements InventoryControllerI<StoreInventory> {

    private final InventoryServiceI<StoreInventory> storeInventoryService;

    private final ItemServiceI itemService;

    /**
     * Instantiates a new Store inventory controller.
     *
     * @param storeInventoryService the store inventory service
     * @param itemService           the item service
     */
    @Autowired
    public StoreInventoryController(InventoryServiceI<StoreInventory> storeInventoryService,
                                    ItemServiceI itemService) {

        this.storeInventoryService = storeInventoryService;
        this.itemService = itemService;
    }

    @PostMapping(value = "/add/{itemId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@RequestBody StoreInventory storeInventory, @PathVariable UUID itemId)
            throws InventoryAlreadyExistsException, InvalidItemException, ItemNotFoundException, InvalidInventoryException {

        if (!ObjectUtils.isEmpty(storeInventory)) {
            if (itemService.existsById(itemId)) {
                if (!storeInventoryService.existsById(itemId)) {

                    storeInventory.setInventoryUUID(itemId);
                    storeInventoryService.add(storeInventory);

                    HttpHeaders responseHeaders = new HttpHeaders();
                    responseHeaders.set("StoreInventoryController", "add");
                    return new ResponseEntity<>("Successfully Created Item", responseHeaders, HttpStatus.CREATED);
                } else
                    throw new InventoryAlreadyExistsException();
            } else
                throw new ItemNotFoundException();
        } else
            throw new InvalidInventoryException();
    }

    @PutMapping(value = "/update/{inventoryId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody StoreInventory storeInventory, @PathVariable UUID inventoryId)
            throws InvalidItemException, ItemNotFoundException {

        if (!ObjectUtils.isEmpty(storeInventory)) {
            if (storeInventoryService.existsById(inventoryId)) {

                storeInventory.setInventoryUUID(inventoryId);
                storeInventoryService.update(storeInventory);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("StoreInventoryController", "update");
                return new ResponseEntity<>("Successfully Updated Item", responseHeaders, HttpStatus.OK);
            } else
                throw new ItemNotFoundException();
        } else
            throw new InvalidItemException();
    }

    @DeleteMapping(value = "/delete/{itemId}")
    public ResponseEntity<String> delete(@PathVariable UUID inventoryId) throws InvalidItemException, ItemNotFoundException {

        if (storeInventoryService.existsById(inventoryId)) {
            storeInventoryService.delete(storeInventoryService.getById(inventoryId));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("StoreInventoryController", "delete");
            return new ResponseEntity<>("Successfully Deleted Item", responseHeaders, HttpStatus.OK);
        } else
            throw new ItemNotFoundException();
    }

    @GetMapping(value = "/get/{itemName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreInventory> getByItemName(@PathVariable String itemName) throws ItemNotFoundException {

        if (storeInventoryService.existsByItemName(itemName)) {

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("StoreInventoryController", "getByItemName");
            StoreInventory storeInventory = storeInventoryService.getByItemName(itemName);
            return new ResponseEntity<>(storeInventory, responseHeaders, HttpStatus.OK);
        } else
            throw new ItemNotFoundException();
    }

    @GetMapping(value = "/get/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreInventory> getById(@PathVariable UUID inventoryId) throws ItemNotFoundException {
        if (storeInventoryService.existsById(inventoryId)) {

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("StoreInventoryController", "getByItemId");
            StoreInventory storeInventory = storeInventoryService.getById(inventoryId);
            return new ResponseEntity<>(storeInventory, responseHeaders, HttpStatus.OK);
        } else
            throw new ItemNotFoundException();
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<StoreInventory>> findAll() {

        List<StoreInventory> allInventories = storeInventoryService.findAll();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("StoreInventoryController", "list");
        return new ResponseEntity<>(allInventories, responseHeaders, HttpStatus.OK);
    }
}
