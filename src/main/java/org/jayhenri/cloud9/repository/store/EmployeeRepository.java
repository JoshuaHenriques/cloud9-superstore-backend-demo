package org.jayhenri.cloud9.repository.store;

import java.util.UUID;

import org.jayhenri.cloud9.model.store.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * The interface Inventory repository.
 */
@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    /**
     * Exists by phone number boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    @Query("select case when count(c)> 0 then true else false end from Employee c where lower(c.phoneNumber) like lower(:phoneNumber)")
    boolean existsByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    /**
     * Exists by email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    @Query("select case when count(c)> 0 then true else false end from Employee c where lower(c.email) like lower(:email)")
    boolean existsByEmail(@Param("email") String email);

    /**
     * Gets by email.
     *
     * @param email the email
     * @return the by email
     */
    @Query(value = "SELECT * FROM employee WHERE employee.email=:email", nativeQuery = true)
    Employee getByEmail(@Param("email") String email);
}