package org.jayhenri.store_manager.controller.store;

import org.jayhenri.store_manager.exception.alreadyexists.StoreAlreadyExistsException;
import org.jayhenri.store_manager.exception.invalid.InvalidPostalCodeException;
import org.jayhenri.store_manager.exception.invalid.InvalidStoreException;
import org.jayhenri.store_manager.exception.notfound.StoreNotFoundException;
import org.jayhenri.store_manager.interfaces.controller.ControllerI;
import org.jayhenri.store_manager.interfaces.service.ServiceI;
import org.jayhenri.store_manager.interfaces.service.customer.AddressServiceI;
import org.jayhenri.store_manager.model.store.Store;
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
 * The type Store controller.
 */
@RestController // Indicates that the data returned by each method will be written straight into
// the response body instead of rendering a template
@RequestMapping("api/store")
public class StoreController implements ControllerI<Store> {

    private final ServiceI<Store> storeService;

    private final AddressServiceI addressService;

    /**
     * Instantiates a new Store controller.
     *
     * @param storeService2  the store service
     * @param addressService the inventory service
     */
    @Autowired
    public StoreController(ServiceI<Store> storeService2, AddressServiceI addressService) {
        this.storeService = storeService2;
        this.addressService = addressService;
    }

    /**
     * Register response entity.
     *
     * @param store the store
     * @return the response entity
     * @throws StoreAlreadyExistsException the store already exists exception
     * @throws InvalidPostalCodeException  the invalid postal code exception
     * @throws InvalidStoreException       the invalid store exception
     */
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@RequestBody Store store)
            throws StoreAlreadyExistsException, InvalidPostalCodeException, InvalidStoreException {

        if (ObjectUtils.isEmpty(store))
            throw new InvalidStoreException();

        else if (storeService.existsById(store.getStoreUUID()))
            throw new StoreAlreadyExistsException();

        else if (!addressService.isValidPostalCode(store.getAddress().getPostalCode()))
            throw new InvalidPostalCodeException();

        storeService.add(store);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("StoreController", "add");
        return new ResponseEntity<>("Successfully Created Store", responseHeaders, HttpStatus.CREATED);
    }

    /**
     * Update store.
     *
     * @param store   the store
     * @param storeId the store id
     * @return the response entity
     * @throws InvalidStoreException  the invalid store exception
     * @throws StoreNotFoundException the store not found exception
     */
    @PutMapping(value = "/update/{storeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody Store store, @PathVariable UUID storeId)
            throws InvalidStoreException, StoreNotFoundException {
        if (!ObjectUtils.isEmpty(store)) {
            if (storeService.existsById(storeId)) {
                storeService.update(storeService.getById(storeId));

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("StoreController", "update");
                return new ResponseEntity<>("Successfully Updated Store", responseHeaders, HttpStatus.OK);
            } else
                throw new StoreNotFoundException();
        } else
            throw new InvalidStoreException();
    }

    /**
     * Delete store.
     *
     * @param storeId the store id
     * @return the response entity
     * @throws StoreNotFoundException the store not found exception
     */
    @DeleteMapping(value = "/delete/{storeId}")
    public ResponseEntity<String> delete(@PathVariable UUID storeId)
            throws StoreNotFoundException {
        if (storeService.existsById(storeId)) {
            storeService.remove(storeService.getById(storeId));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("StoreController", "delete");
            return new ResponseEntity<>("Successfully Deleted Store", responseHeaders, HttpStatus.OK);
        } else
            throw new StoreNotFoundException();
    }

    /**
     * List customers response entity.
     *
     * @return the response entity
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Store>> list() {
        List<Store> list = storeService.findAll();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("StoreController", "list");
        return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
    }

    /**
     * Gets by email.
     *
     * @param storeId the store name
     * @return the by email
     * @throws InvalidNameException   the invalid name exception
     * @throws StoreNotFoundException the store not found exception
     * @throws InvalidStoreException  the invalid store exception
     */
    @GetMapping(value = "/get/{storeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Store> get(@PathVariable UUID storeId)
            throws InvalidNameException, StoreNotFoundException, InvalidStoreException {
        if (!ObjectUtils.isEmpty(storeId)) {
            if (storeService.existsById(storeId)) {
                Store _store = storeService.getById(storeId);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("StoreController", "get");
                return new ResponseEntity<>(_store, responseHeaders, HttpStatus.OK);
            } else
                throw new StoreNotFoundException();
        } else
            throw new InvalidStoreException();
    }
}
