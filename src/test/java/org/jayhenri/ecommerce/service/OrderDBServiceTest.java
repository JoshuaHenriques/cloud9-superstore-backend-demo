package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.model.OrderDB;
import org.jayhenri.ecommerce.repository.OrderDBRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class OrderDBServiceTest {

    @Mock
    OrderDBRepository orderDBRepository;

    @Captor
    ArgumentCaptor<OrderDB> argumentCaptor;

    @Test
    void addOrderToDB() {
        OrderDB orderDB = new OrderDB();
        OrderDBService testMe = new OrderDBService(orderDBRepository);

        testMe.addOrderToDB(orderDB);

        then(orderDBRepository).should().save(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(orderDB);
    }
}