package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.model.Customer;
import org.jayhenri.ecommerce.model.Inventory;
import org.jayhenri.ecommerce.model.Item;
import org.jayhenri.ecommerce.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    InventoryService testMe;

    @Mock
    InventoryRepository inventoryRepository;

    @Captor
    ArgumentCaptor<Inventory> captorInventory;

    @Captor
    ArgumentCaptor<String> captorString;

    Item item;

    @BeforeEach
    void setUp() {
        testMe = new InventoryService(inventoryRepository);
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }

    @Test
    void existsByProductName() {
    }

    @Test
    void getByProductName() {
    }
}