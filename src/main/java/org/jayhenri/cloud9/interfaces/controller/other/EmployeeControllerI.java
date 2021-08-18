package org.jayhenri.cloud9.interfaces.controller.other;

import org.jayhenri.cloud9.exception.alreadyexists.EmployeeAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidEmployeeException;
import org.jayhenri.cloud9.exception.invalid.InvalidPostalCodeException;
import org.jayhenri.cloud9.exception.notfound.EmployeeNotFoundException;
import org.jayhenri.cloud9.model.store.Employee;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import java.util.List;
import java.util.UUID;

public interface EmployeeControllerI {

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> add(@RequestBody Employee employee)
            throws EmployeeAlreadyExistsException, InvalidPostalCodeException, InvalidEmployeeException;

    @PutMapping(value = "/update/{employeeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> update(@RequestBody Employee employee, @PathVariable UUID employeeId)
            throws InvalidEmployeeException, EmployeeNotFoundException;

    @DeleteMapping(value = "/delete/{employeeId}")
    ResponseEntity<String> delete(@PathVariable UUID employeeId)
            throws EmployeeNotFoundException;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Employee>> list();

    @GetMapping(value = "/get/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Employee> get(@PathVariable UUID employeeId)
            throws InvalidNameException, EmployeeNotFoundException, InvalidEmployeeException;
}
