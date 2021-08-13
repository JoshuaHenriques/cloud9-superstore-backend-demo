package org.jayhenri.cloud9.controller.store;

import org.jayhenri.cloud9.exception.alreadyexists.StoreAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidPostalCodeException;
import org.jayhenri.cloud9.exception.invalid.InvalidStoreException;
import org.jayhenri.cloud9.exception.notfound.StoreNotFoundException;
import org.jayhenri.cloud9.model.store.Store;
import org.jayhenri.cloud9.service.customer.AddressService;
import org.jayhenri.cloud9.service.store.StoreService;
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
 * The type Store controller.
 */
@RestController // Indicates that the data returned by each method will be written straight into
// the response body instead of rendering a template
@RequestMapping("api/store")
public class StoreController {

    private final StoreService storeService;
    private final AddressService addressService;

    /**
     * Instantiates a new Store controller.
     *
     * @param storeService   the store service
     * @param addressService the inventory service
     */
    @Autowired
    public StoreController(StoreService storeService, AddressService addressService) {
        this.storeService = storeService;
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
    @PostMapping(value = "/store", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addStore(@RequestBody Store store)
            throws StoreAlreadyExistsException, InvalidPostalCodeException, InvalidStoreException {

        if (ObjectUtils.isEmpty(store))
            throw new InvalidStoreException();

        else if (!addressService.isValidPostalCode(store.getAddress().getPostalCode()))
            throw new InvalidPostalCodeException();

        storeService.add(store);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("StoreController", "addStore");
        return new ResponseEntity<>("Successfully Created Store", responseHeaders, HttpStatus.CREATED);
    }

    /**
     * Update store.
     *
     * @param store the store
     * @return the response entity
     * @throws InvalidStoreException  the invalid store exception
     * @throws StoreNotFoundException the store not found exception
     */
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateStore(@RequestBody Store store, @PathVariable UUID storeId)
            throws InvalidStoreException, StoreNotFoundException {
        if (!ObjectUtils.isEmpty(store)) {
            if (storeService.existsById(storeId)) {
                storeService.update(storeService.getById(storeId));

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("StoreController", "updateStore");
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
    public ResponseEntity<String> deleteStore(@PathVariable UUID storeId)
            throws StoreNotFoundException {
        if (storeService.existsById(storeId)) {
            storeService.delete(storeService.getById(storeId));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("StoreController", "deleteStore");
            return new ResponseEntity<>("Successfully Deleted Store", responseHeaders, HttpStatus.OK);
        } else
            throw new StoreNotFoundException();
    }

    /**
     * List customers response entity.
     *
     * @return the response entity
     */
    @GetMapping(value = "/list/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Store>> listStores() {
        List<Store> list = storeService.findAllStores();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("StoreController", "listStores");
        return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
    }
}
