package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.exception.CustomerAlreadyExistsException;
import org.jayhenri.ecommerce.model.*;
import org.jayhenri.ecommerce.repository.CustomerRepository;
import org.jayhenri.ecommerce.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CustomerRegistrationService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoginRepository loginRepository;

    private Address address;
    private Cart cart;
    private Orders orders;


    public CustomerRegistrationService() { }

    public boolean existsByPhoneNumber(String phoneNumber) {
        return customerRepository.existsCustomerPhoneNumberCustomQuery(phoneNumber);
    }
    public boolean existsByEmail(String email) {
        return loginRepository.existsLoginEmailCustomQuery(email);
    }

    public void saveCustomer(Customer customer, Login login, Address address) throws CustomerAlreadyExistsException {

        if (existsByPhoneNumber(customer.getPhoneNumber()) || existsByEmail(login.getEmail())){
            throw new CustomerAlreadyExistsException();
        }
        customer.setAddress(address);
        customerRepository.save(customer);
        encryptPassword(login);
    }

    public void encryptPassword(Login login) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(login.getPassword());
        login.setPassword(encodedPassword);

        loginRepository.save(login);
    }

}
