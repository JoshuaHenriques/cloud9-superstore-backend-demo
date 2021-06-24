package org.jayhenri.ecommerce.service;

import org.hibernate.annotations.NotFound;
import org.jayhenri.ecommerce.model.Customer;
import org.jayhenri.ecommerce.repository.CustomerRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class CustomerRegistrationService {

    private final CustomerRegistrationRepository cusRegRepo;

    @Autowired
    public CustomerRegistrationService(CustomerRegistrationRepository cusRegRepo) {
        this.cusRegRepo = cusRegRepo;
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Customer> findAll() {
        return cusRegRepo.findAll();
    }

    public Optional<Customer> findById(UUID uuid) {
        return cusRegRepo.findById(uuid);
    }

    public boolean existsById(UUID uuid) { return cusRegRepo.existsById(uuid); }

    // TODO: Implement
    public boolean existsByPhoneNumber(String phoneNumber) {
        return false;
    }

    // TODO: Implement
    public boolean existsByEmail(String email) {
        return false;
    }

    public void save(Customer cus) throws Exception {

        if (existsById(cus.getId())){
            throw new Exception("Customer already exists!");
        }
        cusRegRepo.save(cus);
    }

    // TODO:
}
