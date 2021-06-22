package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.model.*;
import org.jayhenri.ecommerce.repository.CustomerRegistrationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerRegistrationServiceTest {

    @Mock
    private CustomerRegistrationRepository cusRegRepo;

    @InjectMocks
    private CustomerRegistrationService testMe;

    @Captor
    private ArgumentCaptor<Customer> argumentCaptor;

    // Given
    private Customer cus;
    private Address add;
    private Orders ord;
    private Cart cart;
    private LoginInformation login;
    private UUID uuid;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID();
        testMe = new CustomerRegistrationService(cusRegRepo);
        cus = new Customer(
                uuid,
                "Joshua",
                "Anthony",
                "Henriques",
                null,
                null,
                null,
                null,
                null
                );
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void itShouldFindAll() {
        // When
        testMe.findAll();
        // Then
        verify(cusRegRepo).findAll();
    }

    @Test
    void itShouldRegisterCustomer() {
        // When
        testMe.save(cus);
        // Then
//        Optional<Customer> optCus = cusRegRepo.findById(cus.getId());
//        assertThat(optCus)
//                .isPresent()
//                .hasValueSatisfying(c -> {
//                    assertThat(c).isEqualTo(cus);
//                });
        then(cusRegRepo).should().save(argumentCaptor.capture());
        Customer customerACV = argumentCaptor.getValue();
        assertThat(customerACV).isEqualTo(cus);
        verify(cusRegRepo).save(customerACV);
    }
}