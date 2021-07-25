package org.jayhenri.ecommerce.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class InventoryRepositoryDataJpaTest {

    @Autowired
    private TestEntityManager entityManger;
    
    @Autowired
    private InventoryRepository testMe;

    @Test
    void existsByProductName() {
        boolean exists = testMe.existsByProductName("Test");

        assertThat(exists).isTrue();
    }
}
