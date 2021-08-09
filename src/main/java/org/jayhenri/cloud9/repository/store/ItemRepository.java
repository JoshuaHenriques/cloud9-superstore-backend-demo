package org.jayhenri.cloud9.repository.store;

import org.jayhenri.cloud9.model.store.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * The interface Inventory repository.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

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
    Item getByEmail(@Param("email") String email);
}