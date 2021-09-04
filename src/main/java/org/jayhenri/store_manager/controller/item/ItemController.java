package org.jayhenri.store_manager.controller.item;

import org.jayhenri.store_manager.exception.alreadyexists.InventoryAlreadyExistsException;
import org.jayhenri.store_manager.exception.alreadyexists.ItemAlreadyExistsException;
import org.jayhenri.store_manager.exception.invalid.InvalidItemException;
import org.jayhenri.store_manager.exception.notfound.ItemNotFoundException;
import org.jayhenri.store_manager.interfaces.controller.ControllerI;
import org.jayhenri.store_manager.interfaces.service.other.ItemServiceI;
import org.jayhenri.store_manager.model.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import java.util.List;
import java.util.UUID;

/**
 * The type Item controller.
 */
@RestController
@RequestMapping("api/item")
public class ItemController implements ControllerI<Item> {

    private final ItemServiceI itemService;

    /**
     * Instantiates a new Item controller.
     *
     * @param itemService the item service
     */
    @Autowired
    public ItemController(ItemServiceI itemService) {

        this.itemService = itemService;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@RequestBody Item item)
            throws ItemAlreadyExistsException, InvalidItemException {

        if (ObjectUtils.isEmpty(item))
            throw new InvalidItemException();

        else if (itemService.existsByItemName(item.getItemName())
                || itemService.existsById(item.getItemUUID()))
            throw new ItemAlreadyExistsException();

        itemService.add(item);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("ItemRegistrationController", "add");
        return new ResponseEntity<>("Successfully Created Item", responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{itemId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody Item item, @PathVariable UUID itemId)
            throws InvalidItemException, ItemNotFoundException, InventoryAlreadyExistsException {
        if (!ObjectUtils.isEmpty(item)) {
            if (itemService.existsByItemName(item.getItemName()) || itemService.existsById(itemId)) {

                item.setItemUUID(itemId);
                itemService.update(item);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("ItemController", "update");
                return new ResponseEntity<>("Successfully Updated Item", responseHeaders, HttpStatus.OK);
            } else
                throw new ItemNotFoundException();
        } else
            throw new InvalidItemException();
    }

    @DeleteMapping(value = "/delete/{itemId}")
    public ResponseEntity<String> delete(@PathVariable UUID itemId)
            throws ItemNotFoundException {
        if (itemService.existsById(itemId)) {
            Item _item = itemService.getById(itemId);
            itemService.remove(_item);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("ItemController", "delete");
            return new ResponseEntity<>("Successfully Deleted Item", responseHeaders, HttpStatus.OK);
        } else
            throw new ItemNotFoundException();
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> list() {

        List<Item> list = itemService.findAll();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("ItemController", "list");
        return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "/get/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> get(@PathVariable UUID itemId)
            throws InvalidNameException, ItemNotFoundException, InvalidItemException {
        if (!ObjectUtils.isEmpty(itemId)) {
            if (itemService.existsById(itemId)) {
                Item _item = itemService.getById(itemId);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("ItemController", "get");
                return new ResponseEntity<>(_item, responseHeaders, HttpStatus.OK);
            } else
                throw new ItemNotFoundException();
        } else
            throw new InvalidItemException();
    }
}
