package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.model.Item;
import org.jayhenri.ecommerce.model.OrderDB;
import org.jayhenri.ecommerce.repository.OrderDBRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderDBServiceTest {

    @InjectMocks
    OrderDBService orderDBService;

    @Mock
    OrderDBRepository orderDBRepository;

    @Captor
    private ArgumentCaptor<OrderDB> argumentCaptor;

    OrderDB orderDB;
    Item item;

    @BeforeEach
    void setUp() {
        item = new Item(
                "Test Product",
                "Test Description",
                9.99
        );

        ArrayList<Item> items = new ArrayList<>();
        items.add(item);

        orderDB = new OrderDB(
                "PROCESSING",
                "testMe@gmail.com",
                items,
                49.99,
                49.99*0.13+9.99
        );
    }

    @Test
    void addOrderToDB() {
        // Given

        // When
        orderDBService.addOrderToDB(orderDB);
        // Then
        then(orderDBRepository).should().save(argumentCaptor.capture()); // BDD
        OrderDB _orderDB = argumentCaptor.getValue();
        assertThat(_orderDB).isEqualTo(orderDB);
    }
}