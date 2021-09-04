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

    public void add(Customer customer) {

        customerRepository.save(customer);
    }

    public void remove(Customer customer) {

        customerRepository.delete(customer);
    }

    public void update(Customer customer) {

        customerRepository.save(customer);
    }

    public List<Customer> findAll() {

        return customerRepository.findAll();
    }

    public boolean existsByEmail(String email) {

        return customerRepository.existsByEmail(email);
    }

    public Customer getByEmail(String email) {

        return customerRepository.getByEmail(email);
    }

    public boolean existsById(UUID uuid) {

        return customerRepository.existsById(uuid);
    }

    public Customer getById(UUID uuid) {

        return customerRepository.getById(uuid);
    }

    public boolean existsByCCN(String ccn) {

        return customerRepository.existsByCreditCardCCN(ccn);
    }

    public boolean existsByPhoneNumber(String phoneNumber) {

        return customerRepository.existsByPhoneNumber(phoneNumber);
    }
}