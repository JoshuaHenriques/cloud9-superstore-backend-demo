package org.jayhenri.cloud9.controller.item;

import org.jayhenri.cloud9.exception.alreadyexists.ItemAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidItemException;
import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
import org.jayhenri.cloud9.model.item.Item;
import org.jayhenri.cloud9.service.item.ItemService;
import org.jayhenri.cloud9.service.item.ReviewService;
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
@RestController // Indicates that the data returned by each method will be written straight into
// the response body instead of rendering a template
@RequestMapping("api/item")
public class ItemController {

    private final ItemService itemService;
    private final ReviewService reviewService;

    /**
     * Instantiates a new Item controller.
     *
     * @param itemService   the item service
     * @param reviewService the review service
     */
    @Autowired
    public ItemController(ItemService itemService, ReviewService reviewService) {
        this.itemService = itemService;
        this.reviewService = reviewService;
    }

    /**
     * Register response entity.
     *
     * @param item the item
     * @return the response entity
     * @throws ItemAlreadyExistsException the item already exists exception
     * @throws InvalidItemException       the invalid item exception
     */
    @PostMapping(value = "/add/item", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addItem(@RequestBody Item item)
            throws ItemAlreadyExistsException, InvalidItemException {

        if (ObjectUtils.isEmpty(item))
            throw new InvalidItemException();

        else if (itemService.existsByItemName(item.getItemName())
                || itemService.existsById(item.getItemUUID()))
            throw new ItemAlreadyExistsException();

        itemService.add(item);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("ItemRegistrationController", "addItem");
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
     * @throws ItemAlreadyExistsException the item already exists exception
     */
    @PutMapping(value = "/update/{itemId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateItem(@RequestBody Item item, @PathVariable UUID itemId)
            throws InvalidItemException, ItemNotFoundException, ItemAlreadyExistsException {
        if (!ObjectUtils.isEmpty(item)) {
            if (itemService.existsByItemName(item.getItemName())) {
                if (itemService.existsById(itemId)) {
                    itemService.update(item);

                    HttpHeaders responseHeaders = new HttpHeaders();
                    responseHeaders.set("ItemController", "updateItem");
                    return new ResponseEntity<>("Successfully Updated Item", responseHeaders, HttpStatus.OK);
                } else
                    throw new ItemAlreadyExistsException();
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
    public ResponseEntity<String> deleteItem(@PathVariable String itemId)
            throws ItemNotFoundException {
        if (itemService.existsByItemName(itemId)) {
            Item _item = itemService.getByItemName(itemId);
            itemService.remove(_item);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("ItemController", "deleteItem");
            return new ResponseEntity<>("Successfully Deleted Item", responseHeaders, HttpStatus.OK);
        } else
            throw new ItemNotFoundException();
    }

    /**
     * List items response entity.
     *
     * @param pageNo   the page no
     * @param pageSize the page size
     * @return the response entity
     */
    @GetMapping(value = "/list/items", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> listItems(@RequestParam(defaultValue = "0") Integer pageNo,
                                                @RequestParam(defaultValue = "50") Integer pageSize) {
        // @RequestParam(defaultValue = "email") String sortBy
        List<Item> list = itemService.findAll(); // sortBy

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("ItemController", "listItems");
        return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
    }

    /**
     * Gets by email.
     *
     * @param itemName the item name
     * @return the by email
     * @throws InvalidNameException  the invalid name exception
     * @throws ItemNotFoundException the item not found exception
     * @throws InvalidItemException  the invalid item exception
     */
    @GetMapping(value = "/{itemName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> getByItemName(@PathVariable String itemName)
            throws InvalidNameException, ItemNotFoundException, InvalidItemException {
        if (!ObjectUtils.isEmpty(itemName)) {
            if (itemService.existsByItemName(itemName)) {
                Item _item = itemService.getByItemName(itemName);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("ItemController", "getByItemName");
                return new ResponseEntity<>(_item, responseHeaders, HttpStatus.OK);
            } else
                throw new ItemNotFoundException();
        } else
            throw new InvalidItemException();
    }
}
