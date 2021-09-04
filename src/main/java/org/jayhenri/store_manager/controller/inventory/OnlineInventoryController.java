package org.jayhenri.store_manager.controller.inventory;

import org.jayhenri.store_manager.exception.alreadyexists.InventoryAlreadyExistsException;
import org.jayhenri.store_manager.exception.invalid.InvalidInventoryException;
import org.jayhenri.store_manager.exception.invalid.InvalidItemException;
import org.jayhenri.store_manager.exception.notfound.ItemNotFoundException;
import org.jayhenri.store_manager.interfaces.controller.other.InventoryControllerI;
import org.jayhenri.store_manager.interfaces.service.other.InventoryServiceI;
import org.jayhenri.store_manager.interfaces.service.other.ItemServiceI;
import org.jayhenri.store_manager.model.inventory.OnlineInventory;
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
 * The type Online inventory controller.
 */
@RestController // Indicates that the data returned by each method will be written straight into
// the response body instead of rendering a template
@RequestMapping("api/inventory/online")
public class OnlineInventoryController implements InventoryControllerI<OnlineInventory> {

    private final InventoryServiceI<OnlineInventory> onlineInventoryService;

    private final ItemServiceI itemService;

    /**
     * Instantiates a new Online inventory controller.
     *
     * @param onlineInventoryService the online inventory service
     * @param itemService            the item service
     */
    @Autowired
    public OnlineInventoryController(InventoryServiceI<OnlineInventory> onlineInventoryService,
                                     ItemServiceI itemService) {

        this.onlineInventoryService = onlineInventoryService;
        this.itemService = itemService;
    }

    @PostMapping(value = "/add/{itemId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@RequestBody OnlineInventory onlineInventory, @PathVariable UUID itemId)
            throws InventoryAlreadyExistsException, InvalidItemException, ItemNotFoundException, InvalidInventoryException {

        if (!ObjectUtils.isEmpty(onlineInventory)) {
            if (itemService.existsById(itemId)) {
                if (!onlineInventoryService.existsById(itemId)) {

                    onlineInventory.setInventoryUUID(itemId);
                    onlineInventoryService.add(onlineInventory);

                    HttpHeaders responseHeaders = new HttpHeaders();
                    responseHeaders.set("OnlineInventoryController", "add");
                    return new ResponseEntity<>("Successfully Created Item", responseHeaders, HttpStatus.CREATED);
                } else
                    throw new InventoryAlreadyExistsException();
            } else
                throw new ItemNotFoundException();
        } else
            throw new InvalidInventoryException();
    }

    @PutMapping(value = "/update/{inventoryId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody OnlineInventory onlineInventory, @PathVariable UUID inventoryId)
            throws InvalidItemException, ItemNotFoundException {

        if (!ObjectUtils.isEmpty(onlineInventory)) {
            if (onlineInventoryService.existsById(inventoryId)) {

                onlineInventory.setInventoryUUID(inventoryId);
                onlineInventoryService.update(onlineInventory);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("OnlineInventoryController", "update");
                return new ResponseEntity<>("Successfully Updated Item", responseHeaders, HttpStatus.OK);
            } else
                throw new ItemNotFoundException();
        } else
            throw new InvalidItemException();
    }

    @DeleteMapping(value = "/delete/{itemId}")
    public ResponseEntity<String> delete(@PathVariable UUID inventoryId) throws InvalidItemException, ItemNotFoundException {

        if (onlineInventoryService.existsById(inventoryId)) {
            onlineInventoryService.delete(onlineInventoryService.getById(inventoryId));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("OnlineInventoryController", "delete");
            return new ResponseEntity<>("Successfully Deleted Item", responseHeaders, HttpStatus.OK);
        } else
            throw new ItemNotFoundException();
    }

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

    @GetMapping(value = "/get/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OnlineInventory> getById(@PathVariable UUID inventoryId) throws ItemNotFoundException {
        if (onlineInventoryService.existsById(inventoryId)) {

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("OnlineInventoryController", "getByItemId");
            OnlineInventory onlineInventory = onlineInventoryService.getById(inventoryId);
            return new ResponseEntity<>(onlineInventory, responseHeaders, HttpStatus.OK);
        } else
            throw new ItemNotFoundException();
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<OnlineInventory>> findAll() {

        List<OnlineInventory> allInventories = onlineInventoryService.findAll();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("OnlineInventoryController", "list");
        return new ResponseEntity<>(allInventories, responseHeaders, HttpStatus.OK);
    }
}
