package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.model.Customer;
import org.jayhenri.ecommerce.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Customer registration service.
 */
@Service
public class CustomerRegistrationService {


    private final CustomerRepository customerRepository;

    private static final String REGEX_POSTAL_CODE = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";

    /**
     * Instantiates a new Customer registration service.
     *
     * @param customerRepository the customer repository
     */
    @Autowired
    public CustomerRegistrationService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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
     * Exists by phone number boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    public boolean existsByPhoneNumber(String phoneNumber) {
        return customerRepository.existsByPhoneNumber(phoneNumber);
    }

    /**
     * Is valid postal code boolean.
     *
     * @param postalCode the postal code
     * @return the boolean
     */
    public boolean isValidPostalCode(String postalCode) {
        Pattern pattern = Pattern.compile(REGEX_POSTAL_CODE);
        Matcher matcher = pattern.matcher(postalCode);
        return matcher.matches();
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
}