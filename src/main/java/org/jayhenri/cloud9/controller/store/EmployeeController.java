package org.jayhenri.cloud9.controller.store;

import org.jayhenri.cloud9.exception.alreadyexists.EmployeeAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidEmployeeException;
import org.jayhenri.cloud9.exception.invalid.InvalidPostalCodeException;
import org.jayhenri.cloud9.exception.notfound.EmployeeNotFoundException;
import org.jayhenri.cloud9.model.store.Employee;
import org.jayhenri.cloud9.service.customer.AddressService;
import org.jayhenri.cloud9.service.store.EmployeeService;
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
 * The type Employee controller.
 */
@RestController // Indicates that the data returned by each method will be written straight into
// the response body instead of rendering a template
@RequestMapping("api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final AddressService addressService;


    /**
     * Instantiates a new Employee controller.
     *
     * @param employeeService the employee service
     * @param addressService  the address service
     */
    @Autowired
    public EmployeeController(EmployeeService employeeService, AddressService addressService) {
        this.employeeService = employeeService;
        this.addressService = addressService;
    }

    /**
     * Register response entity.
     *
     * @param employee the employee
     * @return the response entity
     * @throws EmployeeAlreadyExistsException the employee already exists exception
     * @throws InvalidPostalCodeException     the invalid postal code exception
     * @throws InvalidEmployeeException       the invalid employee exception
     */
    @PostMapping(value = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee)
            throws EmployeeAlreadyExistsException, InvalidPostalCodeException, InvalidEmployeeException {

        if (ObjectUtils.isEmpty(employee))
            throw new InvalidEmployeeException();

        else if (employeeService.existsById(employee.getEmployeeUUID()))
            throw new EmployeeAlreadyExistsException();

        else if (!addressService.isValidPostalCode(employee.getAddress().getPostalCode()))
            throw new InvalidPostalCodeException();

        employeeService.add(employee);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("EmployeeController", "register");
        return new ResponseEntity<>("Successfully Created Employee", responseHeaders, HttpStatus.CREATED);
    }

    /**
     * Update employee.
     *
     * @param employee   the employee
     * @param employeeId the employee id
     * @return the response entity
     * @throws InvalidEmployeeException  the invalid employee exception
     * @throws EmployeeNotFoundException the employee not found exception
     */
    @PutMapping(value = "/update/{employeeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee, @PathVariable UUID employeeId)
            throws InvalidEmployeeException, EmployeeNotFoundException {
        if (!ObjectUtils.isEmpty(employee)) {
            if (employeeService.existsById(employee.getEmployeeUUID())) {
                employee.setEmployeeUUID(employeeId);
                employeeService.update(employee);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("EmployeeController", "updateEmployee");
                return new ResponseEntity<>("Successfully Updated Employee", responseHeaders, HttpStatus.OK);
            } else
                throw new EmployeeNotFoundException();
        } else
            throw new InvalidEmployeeException();
    }

    /**
     * Delete employee.
     *
     * @param employeeId the employee id
     * @return the response entity
     * @throws EmployeeNotFoundException the employee not found exception
     */
    @DeleteMapping(value = "/delete/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable UUID employeeId)
            throws EmployeeNotFoundException {
        if (employeeService.existsById(employeeId)) {
            employeeService.remove(employeeService.getById(employeeId));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("EmployeeController", "deleteEmployee");
            return new ResponseEntity<>("Successfully Deleted Employee", responseHeaders, HttpStatus.OK);
        } else
            throw new EmployeeNotFoundException();
    }

    /**
     * List customers response entity.
     *
     * @return the response entity
     */
    @GetMapping(value = "/list/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> listEmployees() {
        List<Employee> list = employeeService.findAll();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("EmployeeController", "listEmployees");
        return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
    }
}
