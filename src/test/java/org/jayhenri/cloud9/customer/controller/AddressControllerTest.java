package org.jayhenri.cloud9.customer.controller;

import org.jayhenri.store_manager.controller.customer.AddressController;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * The type Address controller test.
 */
@ExtendWith(MockitoExtension.class)
public class AddressControllerTest {

    private AddressControllerI addressController;

    @Captor
    private ArgumentCaptor<Customer> captorCustomer;

    @Captor
    private ArgumentCaptor<Employee> captorEmployee;

    @Captor
    private ArgumentCaptor<Store> captorStore;

    @Mock
    private CustomerServiceI customerService;

    @Mock
    private ServiceI<Store> storeService;

    @Mock
    private EmployeeServiceI employeeService;

    private UUID eleId;

    private Address address;

    private Customer customer;

    private Employee employee;

    private Store store;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        eleId = UUID.randomUUID();

        address = new Address();

        customer = new Customer();
        customer.setCustomerUUID(eleId);
        customer.setAddress(address);

        employee = new Employee();
        employee.setEmployeeUUID(eleId);
        employee.setAddress(address);

        store = new Store();
        store.setStoreUUID(eleId);
        store.setAddress(address);

        addressController = new AddressController(customerService, storeService, employeeService);
    }

    /**
     * Update customer.
     *
     * @throws CustomerNotFoundException the customer not found exception
     * @throws InvalidAddressException   the invalid address exception
     * @throws StoreNotFoundException    the store not found exception
     * @throws EmployeeNotFoundException the employee not found exception
     */
    @Test
    void updateCustomer() throws CustomerNotFoundException, InvalidAddressException, StoreNotFoundException,
            EmployeeNotFoundException {

        given(customerService.existsById(eleId)).willReturn(true);
        given(customerService.getById(eleId)).willReturn(customer);

        assertThat(HttpStatus.OK).isEqualTo(addressController.update(address, eleId, "customer").getStatusCode());

        then(customerService).should().update(captorCustomer.capture());

        assertThat(customer).isEqualTo(captorCustomer.getValue());
    }

    /**
     * Update customer throws customer not found exception.
     *
     * @throws CustomerNotFoundException the customer not found exception
     * @throws InvalidAddressException   the invalid address exception
     * @throws StoreNotFoundException    the store not found exception
     * @throws EmployeeNotFoundException the employee not found exception
     */
    @Test
    void updateCustomerThrowsCustomerNotFoundException() throws CustomerNotFoundException, InvalidAddressException,
            StoreNotFoundException, EmployeeNotFoundException {

        given(customerService.existsById(eleId)).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> addressController.update(address, eleId, "customer"));
    }

    /**
     * Update employee.
     *
     * @throws CustomerNotFoundException the customer not found exception
     * @throws InvalidAddressException   the invalid address exception
     * @throws StoreNotFoundException    the store not found exception
     * @throws EmployeeNotFoundException the employee not found exception
     */
    @Test
    void updateEmployee() throws CustomerNotFoundException, InvalidAddressException, StoreNotFoundException,
            EmployeeNotFoundException {

        given(employeeService.existsById(eleId)).willReturn(true);
        given(employeeService.getById(eleId)).willReturn(employee);

        assertThat(HttpStatus.OK).isEqualTo(addressController.update(address, eleId, "employee").getStatusCode());

        then(employeeService).should().update(captorEmployee.capture());

        assertThat(employee).isEqualTo(captorEmployee.getValue());
    }

    /**
     * Update employee throws employee not found exception.
     *
     * @throws CustomerNotFoundException the customer not found exception
     * @throws InvalidAddressException   the invalid address exception
     * @throws StoreNotFoundException    the store not found exception
     * @throws EmployeeNotFoundException the employee not found exception
     */
    @Test
    void updateEmployeeThrowsEmployeeNotFoundException() throws CustomerNotFoundException, InvalidAddressException,
            StoreNotFoundException, EmployeeNotFoundException {

        given(employeeService.existsById(eleId)).willReturn(false);

        assertThrows(EmployeeNotFoundException.class, () -> addressController.update(address, eleId, "employee"));
    }

    /**
     * Update store.
     *
     * @throws CustomerNotFoundException the customer not found exception
     * @throws InvalidAddressException   the invalid address exception
     * @throws StoreNotFoundException    the store not found exception
     * @throws EmployeeNotFoundException the employee not found exception
     */
    @Test
    void updateStore() throws CustomerNotFoundException, InvalidAddressException, StoreNotFoundException,
            EmployeeNotFoundException {

        given(storeService.existsById(eleId)).willReturn(true);
        given(storeService.getById(eleId)).willReturn(store);

        assertThat(HttpStatus.OK).isEqualTo(addressController.update(address, eleId, "store").getStatusCode());

        then(storeService).should().update(captorStore.capture());

        assertThat(store).isEqualTo(captorStore.getValue());
    }

    /**
     * Update store throws store not found exception.
     *
     * @throws CustomerNotFoundException the customer not found exception
     * @throws InvalidAddressException   the invalid address exception
     * @throws StoreNotFoundException    the store not found exception
     * @throws EmployeeNotFoundException the employee not found exception
     */
    @Test
    void updateStoreThrowsStoreNotFoundException() throws CustomerNotFoundException, InvalidAddressException,
            StoreNotFoundException, EmployeeNotFoundException {

        given(storeService.existsById(eleId)).willReturn(false);

        assertThrows(StoreNotFoundException.class, () -> addressController.update(address, eleId, "store"));
    }

    /**
     * Update returns wrong type.
     *
     * @throws CustomerNotFoundException the customer not found exception
     * @throws InvalidAddressException   the invalid address exception
     * @throws StoreNotFoundException    the store not found exception
     * @throws EmployeeNotFoundException the employee not found exception
     */
    @Test
    void updateReturnsWrongType() throws CustomerNotFoundException, InvalidAddressException, StoreNotFoundException,
            EmployeeNotFoundException {

        assertThat(HttpStatus.OK).isEqualTo(addressController.update(address, eleId, "wrong type").getStatusCode());
        assertThat("Wrong Type").isEqualTo(addressController.update(address, eleId, "wrong type").getBody());
    }

    /**
     * Update throws invalid address exception.
     *
     * @throws CustomerNotFoundException the customer not found exception
     * @throws InvalidAddressException   the invalid address exception
     * @throws StoreNotFoundException    the store not found exception
     * @throws EmployeeNotFoundException the employee not found exception
     */
    @Test
    void updateThrowsInvalidAddressException() throws CustomerNotFoundException, InvalidAddressException,
            StoreNotFoundException, EmployeeNotFoundException {

        assertThrows(InvalidAddressException.class, () -> addressController.update(null, null, null));
    }
}