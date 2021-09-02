package org.jayhenri.store_manager.interfaces.controller.customer;

import java.util.UUID;

import org.jayhenri.store_manager.exception.invalid.InvalidAddressException;
import org.jayhenri.store_manager.exception.notfound.CustomerNotFoundException;
import org.jayhenri.store_manager.exception.notfound.EmployeeNotFoundException;
import org.jayhenri.store_manager.exception.notfound.StoreNotFoundException;
import org.jayhenri.store_manager.model.customer.Address;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The interface Address controller i.
 */
@RequestMapping("api/address")
public interface AddressControllerI {

    /**
     * Update response entity.
     *
     * @param address the address
     * @param uuid    the uuid
     * @param entity  the entity
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     * @throws InvalidAddressException   the invalid address exception
     * @throws StoreNotFoundException    the store not found exception
     * @throws EmployeeNotFoundException the employee not found exception
     */
    @PutMapping(value = "/update/{entity}/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> update(@RequestBody Address address, UUID uuid, String entity) throws CustomerNotFoundException, InvalidAddressException, StoreNotFoundException, EmployeeNotFoundException;
}
