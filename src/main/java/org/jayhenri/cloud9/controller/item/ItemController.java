package org.jayhenri.cloud9.controller.item;

import java.util.List;
import java.util.UUID;

import javax.naming.InvalidNameException;

import org.jayhenri.cloud9.exception.alreadyexists.InventoryAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidItemException;
import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
import org.jayhenri.cloud9.interfaces.controller.ControllerI;
import org.jayhenri.cloud9.interfaces.service.other.ItemServiceI;
import org.jayhenri.cloud9.model.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * Register response entity.
     *
     * @param item the item
     * @return the response entity
     * @throws InventoryAlreadyExistsException the item already exists exception
     * @throws InvalidItemException       the invalid item exception
     */
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@RequestBody Item item)
            throws InventoryAlreadyExistsException, InvalidItemException {

        if (ObjectUtils.isEmpty(item))
            throw new InvalidItemException();

        else if (itemService.existsByItemName(item.getItemName())
                || itemService.existsById(item.getItemUUID()))
            throw new InventoryAlreadyExistsException();

        itemService.add(item);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("ItemRegistrationController", "add");
        return new ResponseEntity<>("Successfully Created Item", responseHeaders, HttpStatus.CREATED);
    }

    /**
     * Update item.
     *
     * @param item   the item
     * @param itemId the item id
     * @return the response entity
     * @throws InvalidItemException       the invalid item exception
     * @throws ItemNotFoundException      the item not found exception
     * @throws InventoryAlreadyExistsException the item already exists exception
     */
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

    /**
     * Delete item.
     *
     * @param itemId the item id
     * @return the response entity
     * @throws ItemNotFoundException the item not found exception
     */
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

    /**
     * List items response entity.
     *
     * @return the response entity
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> list() {

        List<Item> list = itemService.findAll();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("ItemController", "list");
        return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
    }

    /**
     * Gets by email.
     *
     * @return the by email
     * @throws InvalidNameException  the invalid name exception
     * @throws ItemNotFoundException the item not found exception
     * @throws InvalidItemException  the invalid item exception
     */
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
