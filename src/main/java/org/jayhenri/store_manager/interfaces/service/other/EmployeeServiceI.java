package org.jayhenri.store_manager.interfaces.service.other;

import org.jayhenri.store_manager.interfaces.service.ServiceI;
import org.jayhenri.store_manager.model.store.Employee;

/**
 * The interface Employee service i.
 */
public interface EmployeeServiceI extends ServiceI<Employee> {

    /**
     * Exists by email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    boolean existsByEmail(String email);

    /**
     * Exists by phone number boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    boolean existsByPhoneNumber(String phoneNumber);

    /**
     * Gets by email.
     *
     * @param email the email
     * @return the by email
     */
    Employee getByEmail(String email);
}
