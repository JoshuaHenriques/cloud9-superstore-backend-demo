package org.jayhenri.cloud9.interfaces.controller.other;

import org.jayhenri.cloud9.exception.alreadyexists.ItemAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidItemException;
import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
import org.jayhenri.cloud9.model.item.Item;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface InventoryControllerI<T> {

    @PostMapping(value = "/add/{itemId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> add(@RequestBody Item item, @PathVariable UUID itemId, @RequestBody int quantity, @RequestBody double price)
            throws ItemAlreadyExistsException, InvalidItemException;

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> update(@RequestBody T t)
            throws InvalidItemException, ItemNotFoundException;

    @GetMapping(value = "/get/{itemName}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<T> get(@PathVariable String itemName) throws ItemNotFoundException;

    @DeleteMapping(value = "/remove/{itemName}")
    ResponseEntity<String> remove(@PathVariable String itemName)
            throws InvalidItemException, ItemNotFoundException;

    @GetMapping(value = "/list")
    ResponseEntity<List<T>> findAll();
}
