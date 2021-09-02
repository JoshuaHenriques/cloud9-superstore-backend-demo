package org.jayhenri.store_manager.service.customer;

import org.jayhenri.store_manager.interfaces.service.customer.CustomerServiceI;
import org.jayhenri.store_manager.model.customer.Customer;
import org.jayhenri.store_manager.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


/**
 * The type Customer service.
 */
@Service
public class CustomerService implements CustomerServiceI {

    private final CustomerRepository customerRepository;

    /**
     * Instantiates a new Customer service.
     *
     * @param customerRepository the customer repository
     */
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
        // this.orderDBService = orderDBService;
    }

    /**
     * Add.
     *
     * @param customer the customer
     */
    public void add(Customer customer) {

        customerRepository.save(customer);
    }

    /**
     * Delete.
     *
     * @param customer the customer
     */
    public void remove(Customer customer) {

        customerRepository.delete(customer);
    }

    /**
     * Update.
     *
     * @param customer the customer
     */
    public void update(Customer customer) {

        customerRepository.save(customer);
    }

    /**
     * Find all customers list.
     *
     * @return the list
     */
    public List<Customer> findAll() {

        return customerRepository.findAll();
    }

    /**
     * Exists by email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean existsByEmail(String email) {

        return customerRepository.existsByEmail(email);
    }

    /**
     * Gets by email.
     *
     * @param email the email
     * @return the by email
     */
    public Customer getByEmail(String email) {

        return customerRepository.getByEmail(email);
    }

    /**
     * Exists by email boolean.
     *
     * @param uuid the email
     * @return the boolean
     */
    public boolean existsById(UUID uuid) {

        return customerRepository.existsById(uuid);
    }

    /**
     * Gets by email.
     *
     * @param uuid the email
     * @return the by email
     */
    public Customer getById(UUID uuid) {

        return customerRepository.getById(uuid);
    }

    /**
     * Exists by email boolean.
     *
     * @param ccn the credit card number
     * @return the boolean
     */
    public boolean existsByCCN(String ccn) {

        return customerRepository.existsByCreditCardCCN(ccn);
    }

    /**
     * Exists by phone number boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    public boolean existsByPhoneNumber(String phoneNumber) {

        return customerRepository.existsByPhoneNumber(phoneNumber);
    }
}