package org.jayhenri.cloud9.controller.customer;

import org.jayhenri.cloud9.exception.invalid.InvalidAddressException;
import org.jayhenri.cloud9.exception.notfound.CustomerNotFoundException;
import org.jayhenri.cloud9.exception.notfound.EmployeeNotFoundException;
import org.jayhenri.cloud9.exception.notfound.StoreNotFoundException;
import org.jayhenri.cloud9.interfaces.controller.customer.AddressControllerI;
import org.jayhenri.cloud9.interfaces.service.ServiceI;
import org.jayhenri.cloud9.interfaces.service.customer.CustomerServiceI;
import org.jayhenri.cloud9.interfaces.service.other.EmployeeServiceI;
import org.jayhenri.cloud9.model.customer.Address;
import org.jayhenri.cloud9.model.customer.Customer;
import org.jayhenri.cloud9.model.store.Employee;
import org.jayhenri.cloud9.model.store.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> update(@RequestBody Address address, @PathVariable("uuid") UUID uuid, String type)
            throws CustomerNotFoundException, InvalidAddressException, StoreNotFoundException, EmployeeNotFoundException {
        if (!ObjectUtils.isEmpty(address)) {

            switch (type) {
                case "customer":
                    Customer customer = new Customer();

                    if (customerService.existsById(uuid)) {
                        customer.setCustomerUUID(uuid);
                        customer.setAddress(address);
                        customerService.update(customer);

                        HttpHeaders responseHeaders = new HttpHeaders();
                        responseHeaders.set("AddressController", "update");
                        return new ResponseEntity<>("Successfully Updated Customer's Address", responseHeaders, HttpStatus.OK);
                    } else
                        throw new CustomerNotFoundException();

                case "employee":
                    Employee employee = new Employee();

                    if (employeeService.existsById(uuid)) {
                        employee.setEmployeeUUID(uuid);
                        employee.setAddress(address);
                        employeeService.update(employee);

                        HttpHeaders responseHeaders = new HttpHeaders();
                        responseHeaders.set("AddressController", "update");
                        return new ResponseEntity<>("Successfully Updated Employee's Address", responseHeaders, HttpStatus.OK);
                    } else
                        throw new EmployeeNotFoundException();

                case "store":
                    Store store = new Store();

                    if (storeService.existsById(uuid)) {
                        store.setStoreUUID(uuid);
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
