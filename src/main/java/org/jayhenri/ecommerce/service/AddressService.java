package org.jayhenri.ecommerce.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Address service.
 */
// todo: validate all fields
@Service
public class AddressService {

    private static final String REGEX_POSTAL_CODE = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";

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

}