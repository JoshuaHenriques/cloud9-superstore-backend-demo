package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.exception.CustomerAlreadyExistsException;
import org.jayhenri.ecommerce.exception.InvalidPostalCodeException;
import org.jayhenri.ecommerce.model.*;
import org.jayhenri.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomerRegistrationService {

    @Autowired
    private CustomerRepository customerRepository;

    private static final String REGEX_POSTAL_CODE = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";

    public CustomerRegistrationService() { }

    private boolean existsByPhoneNumber(String phoneNumber) {
        return customerRepository.existsCustomerPhoneNumberCustomQuery(phoneNumber);
    }

    public void saveCustomer(Customer customer, UUID uuid) throws CustomerAlreadyExistsException, InvalidPostalCodeException {

        if (existsByPhoneNumber(customer.getPhoneNumber()))
            throw new CustomerAlreadyExistsException();

        else if (!isValidPostalCode(customer.getAddress().getPostalCode()))
            throw new InvalidPostalCodeException();

        customer.setUuid(uuid);
        customerRepository.save(customer);
    }

    private boolean isValidPostalCode(String postalCode) {
        Pattern pattern = Pattern.compile(REGEX_POSTAL_CODE);
        Matcher matcher = pattern.matcher(postalCode);
        return matcher.matches();
    }
}
