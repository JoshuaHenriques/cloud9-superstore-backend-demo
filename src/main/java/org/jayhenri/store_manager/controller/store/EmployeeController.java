package org.jayhenri.store_manager.controller.store;

import org.jayhenri.store_manager.exception.alreadyexists.EmployeeAlreadyExistsException;
import org.jayhenri.store_manager.exception.invalid.InvalidEmployeeException;
import org.jayhenri.store_manager.exception.invalid.InvalidPostalCodeException;
import org.jayhenri.store_manager.exception.notfound.EmployeeNotFoundException;
import org.jayhenri.store_manager.interfaces.controller.other.EmployeeControllerI;
import org.jayhenri.store_manager.interfaces.service.customer.AddressServiceI;
import org.jayhenri.store_manager.interfaces.service.other.EmployeeServiceI;
import org.jayhenri.store_manager.model.store.Employee;
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
 * The type Employee controller.
 */
@RestController
@RequestMapping("api/employee")
public class EmployeeController implements EmployeeControllerI {

    private final EmployeeServiceI employeeService;
    private final AddressServiceI addressService;


    /**
     * Instantiates a new Employee controller.
     *
     * @param employeeService the employee service
     * @param addressService  the address service
     */
    @Autowired
    public EmployeeController(EmployeeServiceI employeeService, AddressServiceI addressService) {
        this.employeeService = employeeService;
        this.addressService = addressService;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@RequestBody Employee employee)
            throws EmployeeAlreadyExistsException, InvalidPostalCodeException, InvalidEmployeeException {

        if (ObjectUtils.isEmpty(employee))
            throw new InvalidEmployeeException();

        else if (employeeService.existsById(employee.getEmployeeUUID()))
            throw new EmployeeAlreadyExistsException();

        else if (!addressService.isValidPostalCode(employee.getAddress().getPostalCode()))
            throw new InvalidPostalCodeException();

        employeeService.add(employee);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("EmployeeController", "add");
        return new ResponseEntity<>("Successfully Created Employee", responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{employeeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody Employee employee, @PathVariable UUID employeeId)
            throws InvalidEmployeeException, EmployeeNotFoundException {
        if (!ObjectUtils.isEmpty(employee)) {
            if (employeeService.existsById(employeeId)) {
                employee.setEmployeeUUID(employeeId);
                employeeService.update(employee);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("EmployeeController", "update");
                return new ResponseEntity<>("Successfully Updated Employee", responseHeaders, HttpStatus.OK);
            } else
                throw new EmployeeNotFoundException();
        } else
            throw new InvalidEmployeeException();
    }

    @DeleteMapping(value = "/delete/{employeeId}")
    public ResponseEntity<String> delete(@PathVariable UUID employeeId)
            throws EmployeeNotFoundException {
        if (employeeService.existsById(employeeId)) {
            employeeService.remove(employeeService.getById(employeeId));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("EmployeeController", "delete");
            return new ResponseEntity<>("Successfully Deleted Employee", responseHeaders, HttpStatus.OK);
        } else
            throw new EmployeeNotFoundException();
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> list() {
        List<Employee> list = employeeService.findAll();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("EmployeeController", "list");
        return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "/get/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> get(@PathVariable UUID employeeId)
            throws InvalidNameException, EmployeeNotFoundException, InvalidEmployeeException {
        if (!ObjectUtils.isEmpty(employeeId)) {
            if (employeeService.existsById(employeeId)) {
                Employee _employee = employeeService.getById(employeeId);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("EmployeeController", "get");
                return new ResponseEntity<>(_employee, responseHeaders, HttpStatus.OK);
            } else
                throw new EmployeeNotFoundException();
        } else
            throw new InvalidEmployeeException();
    }
}
