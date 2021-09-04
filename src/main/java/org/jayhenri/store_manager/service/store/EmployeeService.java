package org.jayhenri.store_manager.service.store;

import org.jayhenri.store_manager.interfaces.service.other.EmployeeServiceI;
import org.jayhenri.store_manager.model.store.Employee;
import org.jayhenri.store_manager.repository.store.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


/**
 * The type Employee service.
 */
@Service
public class EmployeeService implements EmployeeServiceI {

    private final EmployeeRepository employeeRepository;

    /**
     * Instantiates a new Employee service.
     *
     * @param employeeRepository the employee repository
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
     * @param employee the employee
     */
    public void add(Employee employee) {

        employeeRepository.save(employee);
    }

    /**
     * Delete.
     *
     * @param employee the employee
     */
    public void remove(Employee employee) {

        employeeRepository.delete(employee);
    }

    /**
     * Update.
     *
     * @param employee the employee
     */
    public void update(Employee employee) {

        employeeRepository.save(employee);
    }

    /**
     * Find all employees list.
     *
     * @return the list
     */
    public List<Employee> findAll() {

        return employeeRepository.findAll();
    }

    /**
     * Exists by email boolean.
     *
     * @param uuid the email
     * @return the boolean
     */
    public boolean existsById(UUID uuid) {

        return employeeRepository.existsById(uuid);
    }

    /**
     * Gets by email.
     *
     * @param uuid the email
     * @return the by email
     */
    public Employee getById(UUID uuid) {

        return employeeRepository.getById(uuid);
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