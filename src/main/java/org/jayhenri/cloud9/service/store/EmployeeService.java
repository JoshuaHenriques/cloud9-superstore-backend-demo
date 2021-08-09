package org.jayhenri.cloud9.service.store;

import org.jayhenri.cloud9.model.store.Employee;
import org.jayhenri.cloud9.repository.store.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Employee service.
 */
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    /**
     * Instantiates a new Employee service.
     *
     * @param employeeRepository the customer repository
     */
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
        // this.orderDBService = orderDBService;
    }

    /**
     * Exists by phone number boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    public boolean existsByPhoneNumber(String phoneNumber) {

        return employeeRepository.existsByPhoneNumber(phoneNumber);
    }

    /**
     * Add.
     *
     * @param customer the customer
     */
    public void add(Employee customer) {

        employeeRepository.save(customer);
    }

    /**
     * Delete.
     *
     * @param customer the customer
     */
    public void delete(Employee customer) {

        employeeRepository.delete(customer);
    }

    /**
     * Update.
     *
     * @param customer the customer
     */
    public void update(Employee customer) {

        employeeRepository.save(customer);
    }

    /**
     * Find all customers list.
     *
     * @param pageNo   the page no
     * @param pageSize the page size
     * @return the list
     */
    public List<Employee> findAllEmployees(Integer pageNo, Integer pageSize) {
        // String sortBy
        Pageable paging = PageRequest.of(pageNo, pageSize); // Sort.by(sortBy).ascending()
        Page<Employee> pagedResult = employeeRepository.findAll(paging);

        if (pagedResult.hasContent()) return pagedResult.getContent();
        else return new ArrayList<>();
    }

    /**
     * Exists by email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean existsByEmail(String email) {

        return employeeRepository.existsByEmail(email);
    }

    /**
     * Gets by email.
     *
     * @param email the email
     * @return the by email
     */
    public Employee getByEmail(String email) {

        return employeeRepository.getByEmail(email);
    }
}