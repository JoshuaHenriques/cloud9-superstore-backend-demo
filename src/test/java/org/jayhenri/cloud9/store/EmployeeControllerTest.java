package org.jayhenri.cloud9.store;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.UUID;

import javax.naming.InvalidNameException;

import org.jayhenri.store_manager.controller.store.EmployeeController;
import org.jayhenri.store_manager.exception.alreadyexists.EmployeeAlreadyExistsException;
import org.jayhenri.store_manager.exception.alreadyexists.InventoryAlreadyExistsException;
import org.jayhenri.store_manager.exception.alreadyexists.ItemAlreadyExistsException;
import org.jayhenri.store_manager.exception.alreadyexists.LoginAlreadyExistsException;
import org.jayhenri.store_manager.exception.invalid.InvalidEmployeeException;
import org.jayhenri.store_manager.exception.invalid.InvalidItemException;
import org.jayhenri.store_manager.exception.invalid.InvalidLoginException;
import org.jayhenri.store_manager.exception.invalid.InvalidPostalCodeException;
import org.jayhenri.store_manager.exception.notfound.EmployeeNotFoundException;
import org.jayhenri.store_manager.exception.notfound.ItemNotFoundException;
import org.jayhenri.store_manager.exception.notfound.LoginNotFoundException;
import org.jayhenri.store_manager.interfaces.controller.other.EmployeeControllerI;
import org.jayhenri.store_manager.interfaces.service.customer.AddressServiceI;
import org.jayhenri.store_manager.interfaces.service.other.EmployeeServiceI;
import org.jayhenri.store_manager.model.customer.Address;
import org.jayhenri.store_manager.model.login.Login;
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


/**
 * The type Employee controller test.
 */
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    private Employee employee;
    @Mock
    private EmployeeServiceI employeeService;

    @Mock
    private AddressServiceI addressService;

    @Captor
    private ArgumentCaptor<Employee> captorEmployee;

    @Captor
    private ArgumentCaptor<String> captorString;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    private EmployeeControllerI employeeController;

    private UUID employeeId;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        employeeId = UUID.randomUUID();

        employeeController = new EmployeeController(employeeService, addressService);

        employee = new Employee(
            "employee.email@gmail.com",
            new Address(),
            new Login(),
            new Store(),
            "First Name",
            "Last Name",
            "2222222222",
            "02/02/0202"
        );

        employee.setEmployeeUUID(employeeId);
    }

    /**
     * Add employee.
     *
     * @throws InventoryAlreadyExistsException the employee already exists exception
     * @throws InvalidEmployeeException       the invalid employee exception
     * @throws EmployeeAlreadyExistsException
     * @throws ItemAlreadyExistsException
     * @throws InvalidItemException
     * @throws InvalidPostalCodeException
     * @throws InvalidLoginException
     * @throws LoginAlreadyExistsException
     */
    @Test
    void addEmployee() throws InventoryAlreadyExistsException, InvalidEmployeeException, EmployeeAlreadyExistsException, LoginAlreadyExistsException, InvalidLoginException, InvalidPostalCodeException, InvalidItemException, ItemAlreadyExistsException {

        given(employeeService.existsById(employeeId)).willReturn(false);
        given(addressService.isValidPostalCode(employee.getAddress().getPostalCode())).willReturn(true);

        assertThat(employeeController.add(employee).getStatusCode()).isEqualTo(HttpStatus.CREATED);

        then(employeeService).should().add(captorEmployee.capture());

        assertThat(captorEmployee.getValue()).isEqualTo(employee);
    }

    @Test
    void addEmployeeThrowsInvalidEmployeeException() {

        assertThrows(InvalidEmployeeException.class, () -> employeeController.add(null));
    }

    @Test
    void addEmployeeThrowsEmployeeAlreadyExistsException() {

        given(employeeService.existsById(employeeId)).willReturn(true);
        
        assertThrows(EmployeeAlreadyExistsException.class, () -> employeeController.add(employee));
    }

    @Test
    void addThrowsInvalidPostalCodeException() {

        given(employeeService.existsById(employeeId)).willReturn(false);
        given(addressService.isValidPostalCode(employee.getAddress().getPostalCode())).willReturn(false);

        assertThrows(InvalidPostalCodeException.class, () -> employeeController.add(employee));
    }

    @Test
    void updateEmployee() throws InventoryAlreadyExistsException, InvalidEmployeeException, EmployeeNotFoundException, InvalidLoginException, LoginNotFoundException, InvalidItemException, ItemNotFoundException {

        given(employeeService.existsById(employeeId)).willReturn(true);

        assertThat(HttpStatus.OK).isEqualTo(employeeController.update(employee, employeeId).getStatusCode());

        then(employeeService).should().update(captorEmployee.capture());

        assertThat(captorEmployee.getValue()).isEqualTo(employee);
    }

    @Test
    void updateEmployeeThrowsInvalidEmployeeException() {

        assertThrows(InvalidEmployeeException.class, () -> employeeController.add(null));
    }

    @Test
    void updateEmployeeThrowsEmployeeNotFoundException() {

        given(employeeService.existsById(employeeId)).willReturn(false);

        assertThrows(EmployeeNotFoundException.class, () -> employeeController.update(employee, employeeId));
    }

    @Test
    void deleteEmployee() throws EmployeeNotFoundException, LoginNotFoundException, ItemNotFoundException {

        given(employeeService.existsById(employeeId)).willReturn(true);
        given(employeeService.getById(employeeId)).willReturn(employee);

        assertThat(HttpStatus.OK).isEqualTo(employeeController.delete(employeeId).getStatusCode());

        then(employeeService).should().remove(captorEmployee.capture());

        assertThat(captorEmployee.getValue()).isEqualTo(employee);
    }

    @Test
    void deleteEmployeeThrowsEmployeeNotFoundException() {

        given(employeeService.existsById(employeeId)).willReturn(false);

        assertThrows(EmployeeNotFoundException.class, () -> employeeController.delete(employeeId));
    }

    @Test
    void list() {

    }

    @Test
    void get() throws InvalidNameException, InvalidEmployeeException, EmployeeNotFoundException, InvalidLoginException, LoginNotFoundException, ItemNotFoundException, InvalidItemException {
        
        given(employeeService.existsById(employeeId)).willReturn(true);
        given(employeeService.getById(employeeId)).willReturn(employee);

        assertThat(HttpStatus.OK).isEqualTo(employeeController.get(employeeId).getStatusCode());
        assertThat(employee).isEqualTo(employeeController.get(employeeId).getBody());
    }

    @Test
    void getEmployeeThrowsEmployeeNotFoundException() {

        given(employeeService.existsById(employeeId)).willReturn(false);

        assertThrows(EmployeeNotFoundException.class, () -> employeeController.get(employeeId));
    }

    @Test
    void getEmployeeThrowsInvalidEmployeeException() {

        assertThrows(InvalidEmployeeException.class, () -> employeeController.get(null));
    }
}
