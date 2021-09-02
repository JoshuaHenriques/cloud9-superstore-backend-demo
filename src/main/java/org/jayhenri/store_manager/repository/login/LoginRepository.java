package org.jayhenri.store_manager.repository.login;

import org.jayhenri.store_manager.model.login.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * The interface Inventory repository.
 */
@Repository
@Transactional
public interface LoginRepository extends JpaRepository<Login, UUID> {

    /**
     * Exists by phone number boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    @Query("select case when count(c)> 0 then true else false end from Customer c where lower(c.phoneNumber) like lower(:phoneNumber)")
    boolean existsByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    /**
     * Exists by email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    @Query("select case when count(c)> 0 then true else false end from Customer c where lower(c.email) like lower(:email)")
    boolean existsByEmail(@Param("email") String email);

    /**
     * Gets by email.
     *
     * @param email the email
     * @return the by email
     */
    @Query(value = "SELECT * FROM customers WHERE customers.email=:email", nativeQuery = true)
    Login getByEmail(@Param("email") String email);
}