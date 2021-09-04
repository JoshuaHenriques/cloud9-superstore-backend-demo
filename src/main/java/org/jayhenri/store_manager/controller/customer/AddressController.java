package org.jayhenri.store_manager.controller.customer;

import org.jayhenri.store_manager.exception.invalid.InvalidAddressException;
import org.jayhenri.store_manager.exception.notfound.CustomerNotFoundException;
import org.jayhenri.store_manager.exception.notfound.EmployeeNotFoundException;
import org.jayhenri.store_manager.exception.notfound.StoreNotFoundException;
import org.jayhenri.store_manager.interfaces.controller.customer.AddressControllerI;
import org.jayhenri.store_manager.interfaces.service.ServiceI;
import org.jayhenri.store_manager.interfaces.service.customer.CustomerServiceI;
import org.jayhenri.store_manager.interfaces.service.other.EmployeeServiceI;
import org.jayhenri.store_manager.model.customer.Address;
import org.jayhenri.store_manager.model.customer.Customer;
import org.jayhenri.store_manager.model.store.Employee;
import org.jayhenri.store_manager.model.store.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * The type Address controller.
 */
@RestController
@RequestMapping("api/address")
public class AddressController implements AddressControllerI {

    private final CustomerServiceI customerService;
    private final ServiceI<Store> storeService;
    private final EmployeeServiceI employeeService;

    /**
     * Instantiates a new Address controller.
     *
     * @param customerService the customer service
     * @param storeService    the store service
     * @param employeeService the employee service
     */
    @Autowired
    public AddressController(CustomerServiceI customerService, ServiceI<Store> storeService, EmployeeServiceI employeeService) {

        this.customerService = customerService;
        this.storeService = storeService;
        this.employeeService = employeeService;
    }

    /**
     * Update customer.
     *
     * @param uuid    the uuid
     * @param address the customer
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     * @throws InvalidAddressException   the invalid address exception
     */
    @PutMapping(value = "/update/{entity}/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody Address address, @PathVariable UUID uuid, String type)
            throws CustomerNotFoundException, InvalidAddressException, StoreNotFoundException, EmployeeNotFoundException {
        if (!ObjectUtils.isEmpty(address) || !ObjectUtils.isEmpty(uuid) || !ObjectUtils.isEmpty(type)) {

            switch (type) {
                case "customer":

                    if (customerService.existsById(uuid)) {
                        Customer customer = customerService.getById(uuid);
                        customer.setAddress(address);
                        customerService.update(customer);

                        HttpHeaders responseHeaders = new HttpHeaders();
                        responseHeaders.set("AddressController", "update");
                        return new ResponseEntity<>("Successfully Updated Customer's Address", responseHeaders, HttpStatus.OK);
                    } else
                        throw new CustomerNotFoundException();

                case "employee":

                    if (employeeService.existsById(uuid)) {
                        Employee employee = employeeService.getById(uuid);
                        employee.setAddress(address);
                        employeeService.update(employee);

                        HttpHeaders responseHeaders = new HttpHeaders();
                        responseHeaders.set("AddressController", "update");
                        return new ResponseEntity<>("Successfully Updated Employee's Address", responseHeaders, HttpStatus.OK);
                    } else
                        throw new EmployeeNotFoundException();

                case "store":

                    if (storeService.existsById(uuid)) {
                        Store store = storeService.getById(uuid);
                        store.setAddress(address);
                        storeService.update(store);

                        HttpHeaders responseHeaders = new HttpHeaders();
                        responseHeaders.set("AddressController", "update");
                        return new ResponseEntity<>("Successfully Updated Store's Address", responseHeaders, HttpStatus.OK);
                    } else
                        throw new StoreNotFoundException();

                default:
                    HttpHeaders responseHeaders = new HttpHeaders();
                    responseHeaders.set("AddressController", "update");
                    return new ResponseEntity<>("Wrong Type", responseHeaders, HttpStatus.OK);
            }
        } else
            throw new InvalidAddressException();
    }
}
