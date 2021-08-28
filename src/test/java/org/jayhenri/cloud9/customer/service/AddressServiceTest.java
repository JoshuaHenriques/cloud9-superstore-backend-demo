package org.jayhenri.cloud9.customer.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.jayhenri.cloud9.interfaces.service.customer.AddressServiceI;
import org.jayhenri.cloud9.model.customer.Address;
import org.jayhenri.cloud9.service.customer.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * The type Address service test.
 */
@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    private AddressServiceI addressService;

    @Captor
    private ArgumentCaptor<Address> captorAddress;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        addressService = new AddressService();
    }

    /**
     * Is valid postal code.
     */
    @Test
    void isValidPostalCode() {
        Boolean bool = addressService.isValidPostalCode("M1C8N3");

        assertThat(bool).isTrue();
    }

    /**
     * Is not valid postal code.
     */
    @Test
    void isNotValidPostalCode() {
        Boolean bool = addressService.isValidPostalCode("M1CM8N3");

        assertThat(bool).isFalse();
    }
}
