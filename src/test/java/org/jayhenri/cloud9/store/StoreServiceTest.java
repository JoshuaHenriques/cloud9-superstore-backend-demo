package org.jayhenri.cloud9.store;

import org.jayhenri.cloud9.model.customer.Address;
import org.jayhenri.cloud9.model.inventory.OnlineInventory;
import org.jayhenri.cloud9.model.inventory.StoreInventory;
import org.jayhenri.cloud9.model.store.Store;
import org.jayhenri.cloud9.repository.store.StoreRepository;
import org.jayhenri.cloud9.service.store.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {

    private Store store;

    private StoreService storeService;

    @Mock
    private StoreRepository storeRepository;

    @Captor
    private ArgumentCaptor<Store> captorStore;

    @Captor
    private ArgumentCaptor<String> captorString;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    private UUID uuid;

    @BeforeEach
    void setUp() {

        storeService = new StoreService(storeRepository);

        store = new Store(
                "Cloud9 Superstore",
                new Address(),
                new HashSet<StoreInventory>(),
                new HashSet<OnlineInventory>()
        );
    }
    /**
     * Test add.
     */
    @Test
    void add() {

        storeService.addStore(store);

        then(storeRepository).should().save(captorStore.capture());

        assertThat(store).isEqualTo(captorStore.getValue());
    }

    /**
     * Test update.
     */
    @Test
    void update() {
        storeService.updateStore(store);

        then(storeRepository).should().save(captorStore.capture());

        assertThat(captorStore.getValue()).isEqualTo(store);
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        storeService.deleteStore(store);

        then(storeRepository).should().delete(captorStore.capture());

        assertThat(captorStore.getValue()).isEqualTo(store);
    }

    /**
     * Find all.
     */
    @Test
    void findAllStores() {
        storeService.findAllStores();

        then(storeRepository).should().findAll();
    }

@Test
    void existsById() {

            given(storeRepository.existsById(uuid)).willReturn(true);

            boolean exists = storeService.existsById(uuid);

            then(storeRepository).should().existsById(captorUUID.capture());

            assertThat(exists).isTrue();
            assertThat(captorUUID.getValue()).isEqualTo(uuid);
            }

@Test
    void doesNotExistsById() {

            given(storeRepository.existsById(uuid)).willReturn(false);

            boolean exists = storeService.existsById(uuid);

            then(storeRepository).should().existsById(captorUUID.capture());

            assertThat(exists).isFalse();
            assertThat(captorUUID.getValue()).isEqualTo(uuid);
            }

@Test
    void getById() {

            given(storeRepository.getById(uuid)).willReturn(store);

            Store _store = storeService.getById(uuid);

            then(storeRepository).should().getById(captorUUID.capture());

            assertThat(_store).isEqualTo(store);
            assertThat(captorUUID.getValue()).isEqualTo(uuid);
            }

}
