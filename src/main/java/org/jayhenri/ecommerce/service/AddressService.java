package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.InvalidNameException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AddressService {

    @Autowired
    private Address address;
    
    private static final String REGEX_POSTAL_CODE = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";

    public AddressService(
            UUID uuid,
            String streetName,
            Long streetNumber,
            Long unitNumber,
            String city,
            String postalCode,
            String province) throws InvalidNameException {

        if (isValidPostalCode(postalCode)) {
            this.address = new Address(
                uuid,
                streetName,
                streetNumber,
                unitNumber,
                city,
                postalCode,
                province
        );
        } else throw new InvalidNameException();
    }

    public boolean isValidPostalCode(String postalCode) {
        Pattern pattern = Pattern.compile(REGEX_POSTAL_CODE);
        Matcher matcher = pattern.matcher(postalCode);
        return matcher.matches();
    }
}
