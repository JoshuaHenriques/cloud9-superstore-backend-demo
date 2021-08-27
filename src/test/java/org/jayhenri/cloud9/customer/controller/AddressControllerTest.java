package org.jayhenri.cloud9.customer.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.UUID;

import org.jayhenri.cloud9.controller.customer.AddressController;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

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

	@Test
	void updateCustomer() throws CustomerNotFoundException, InvalidAddressException, StoreNotFoundException,
			EmployeeNotFoundException {

		given(customerService.existsById(eleId)).willReturn(true);
		given(customerService.getById(eleId)).willReturn(customer);

		assertThat(HttpStatus.OK).isEqualTo(addressController.update(address, eleId, "customer").getStatusCode());

		then(customerService).should().update(captorCustomer.capture());

		assertThat(customer).isEqualTo(captorCustomer.getValue());
	}

	@Test
	void updateCustomerThrowsCustomerNotFoundException() throws CustomerNotFoundException, InvalidAddressException,
			StoreNotFoundException, EmployeeNotFoundException {

		given(customerService.existsById(eleId)).willReturn(false);

		assertThrows(CustomerNotFoundException.class, () -> addressController.update(address, eleId, "customer"));
	}

	@Test
	void updateEmployee() throws CustomerNotFoundException, InvalidAddressException, StoreNotFoundException,
			EmployeeNotFoundException {

		given(employeeService.existsById(eleId)).willReturn(true);
		given(employeeService.getById(eleId)).willReturn(employee);

		assertThat(HttpStatus.OK).isEqualTo(addressController.update(address, eleId, "employee").getStatusCode());

		then(employeeService).should().update(captorEmployee.capture());

		assertThat(employee).isEqualTo(captorEmployee.getValue());
	}

	@Test
	void updateEmployeeThrowsEmployeeNotFoundException() throws CustomerNotFoundException, InvalidAddressException,
			StoreNotFoundException, EmployeeNotFoundException {

		given(employeeService.existsById(eleId)).willReturn(false);

		assertThrows(EmployeeNotFoundException.class, () -> addressController.update(address, eleId, "employee"));
	}

	@Test
	void updateStore() throws CustomerNotFoundException, InvalidAddressException, StoreNotFoundException,
			EmployeeNotFoundException {

		given(storeService.existsById(eleId)).willReturn(true);
		given(storeService.getById(eleId)).willReturn(store);

		assertThat(HttpStatus.OK).isEqualTo(addressController.update(address, eleId, "store").getStatusCode());

		then(storeService).should().update(captorStore.capture());

		assertThat(store).isEqualTo(captorStore.getValue());
	}

	@Test
	void updateStoreThrowsStoreNotFoundException() throws CustomerNotFoundException, InvalidAddressException,
			StoreNotFoundException, EmployeeNotFoundException {

		given(storeService.existsById(eleId)).willReturn(false);

		assertThrows(StoreNotFoundException.class, () -> addressController.update(address, eleId, "store"));
	}

	@Test
	void updateReturnsWrongType() throws CustomerNotFoundException, InvalidAddressException, StoreNotFoundException,
			EmployeeNotFoundException {

		assertThat(HttpStatus.OK).isEqualTo(addressController.update(address, eleId, "wrong type").getStatusCode());
		assertThat("Wrong Type").isEqualTo(addressController.update(address, eleId, "wrong type").getBody());
	}

	@Test
	void updateThrowsInvalidAddressException() throws CustomerNotFoundException, InvalidAddressException,
			StoreNotFoundException, EmployeeNotFoundException {

		assertThrows(InvalidAddressException.class, () -> addressController.update(null, null, null));
	}
}