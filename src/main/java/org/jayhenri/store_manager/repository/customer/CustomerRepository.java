package org.jayhenri.store_manager.repository.customer;

import org.jayhenri.store_manager.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * The interface Customer repository.
 */
@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    /**
     * Exists by email boolean.
     *
     * @param phoneNumber the email
     * @return the boolean
     */
    @Query(value = "SELECT * FROM customers WHERE EXISTS (SELECT phone_number FROM customers WHERE customers.phone_number = :phone_number)", nativeQuery = true)
    boolean existsByPhoneNumber(@Param("phone_number") String phoneNumber);

    /**
     * Exists by email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    @Query(value = "SELECT * FROM customers WHERE EXISTS (SELECT email FROM customers WHERE customers.email = :email)", nativeQuery = true)
    boolean existsByEmail(@Param("email") String email);

    /**
     * Gets by email.
     *
     * @param email the email
     * @return the by email
     */
    @Query(value = "SELECT * FROM customers WHERE customers.email = :email", nativeQuery = true)
    Customer getByEmail(@Param("email") String email);

    /**
     * Gets by email.
     *
     * @param ccn the ccn
     * @return the by email
     */
    @Query(value = "SELECT * FROM credit_cards WHERE EXISTS (SELECT ccn FROM credit_cards WHERE credit_cards.ccn = :ccn", nativeQuery = true)
    boolean existsByCreditCardCCN(@Param("ccn") String ccn);
}
