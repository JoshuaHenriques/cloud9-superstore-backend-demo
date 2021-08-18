package org.jayhenri.cloud9.interfaces.controller;

import org.jayhenri.cloud9.exception.alreadyexists.ItemAlreadyExistsException;
import org.jayhenri.cloud9.exception.alreadyexists.LoginAlreadyExistsException;
import org.jayhenri.cloud9.exception.alreadyexists.StoreAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidItemException;
import org.jayhenri.cloud9.exception.invalid.InvalidLoginException;
import org.jayhenri.cloud9.exception.invalid.InvalidPostalCodeException;
import org.jayhenri.cloud9.exception.invalid.InvalidStoreException;
import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
import org.jayhenri.cloud9.exception.notfound.LoginNotFoundException;
import org.jayhenri.cloud9.exception.notfound.StoreNotFoundException;
import org.jayhenri.cloud9.model.item.Item;
import org.jayhenri.cloud9.model.store.Store;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import java.util.List;
import java.util.UUID;

public interface ControllerI<T> {

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> add(@RequestBody T t) throws LoginAlreadyExistsException, InvalidLoginException, StoreAlreadyExistsException, InvalidPostalCodeException, InvalidStoreException, ItemAlreadyExistsException, InvalidItemException;

    @PutMapping(value = "/update/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> update(@RequestBody T t, @PathVariable UUID uuid) throws InvalidLoginException, LoginNotFoundException, InvalidStoreException, StoreNotFoundException, InvalidItemException, ItemNotFoundException, ItemAlreadyExistsException;

    @DeleteMapping(value = "/delete/{uuid}")
    ResponseEntity<String> delete(@PathVariable UUID uuid) throws LoginNotFoundException, StoreNotFoundException, ItemNotFoundException;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<T>> list();

    @GetMapping(value = "/get/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<T> get(@PathVariable UUID uuid) throws InvalidNameException, InvalidLoginException, LoginNotFoundException, StoreNotFoundException, InvalidStoreException, ItemNotFoundException, InvalidItemException;
}
