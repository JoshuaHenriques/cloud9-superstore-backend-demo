package org.jayhenri.cloud9.store;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.UUID;

import javax.naming.InvalidNameException;

import org.jayhenri.cloud9.controller.store.StoreController;
import org.jayhenri.cloud9.exception.alreadyexists.InventoryAlreadyExistsException;
import org.jayhenri.cloud9.exception.alreadyexists.ItemAlreadyExistsException;
import org.jayhenri.cloud9.exception.alreadyexists.LoginAlreadyExistsException;
import org.jayhenri.cloud9.exception.alreadyexists.StoreAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidItemException;
import org.jayhenri.cloud9.exception.invalid.InvalidLoginException;
import org.jayhenri.cloud9.exception.invalid.InvalidPostalCodeException;
import org.jayhenri.cloud9.exception.invalid.InvalidStoreException;
import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
import org.jayhenri.cloud9.exception.notfound.LoginNotFoundException;
import org.jayhenri.cloud9.exception.notfound.StoreNotFoundException;
import org.jayhenri.cloud9.interfaces.controller.ControllerI;
import org.jayhenri.cloud9.interfaces.service.ServiceI;
import org.jayhenri.cloud9.interfaces.service.customer.AddressServiceI;
import org.jayhenri.cloud9.model.customer.Address;
import org.jayhenri.cloud9.model.store.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;


/**
 * The type Store controller test.
 */
@ExtendWith(MockitoExtension.class)
public class StoreControllerTest {

    private Store store;

    @Mock
    private ServiceI<Store> storeService;

    @Mock
    private AddressServiceI addressService;

    @Captor
    private ArgumentCaptor<Store> captorStore;

    @Captor
    private ArgumentCaptor<String> captorString;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    private ControllerI<Store> storeController;

    private UUID storeId;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        storeId = UUID.randomUUID();

        storeController = new StoreController(storeService, addressService);

        store = new Store(
            "Cloud9",
            new Address()
        );

        store.setStoreUUID(storeId);
    }

    /**
     * Add store.
     *
     * @throws InventoryAlreadyExistsException the store already exists exception
     * @throws InvalidStoreException       the invalid store exception
     * @throws StoreAlreadyExistsException
     * @throws ItemAlreadyExistsException
     * @throws InvalidItemException
     * @throws InvalidPostalCodeException
     * @throws InvalidLoginException
     * @throws LoginAlreadyExistsException
     */
    @Test
    void addStore() throws InventoryAlreadyExistsException, InvalidStoreException, StoreAlreadyExistsException, LoginAlreadyExistsException, InvalidLoginException, InvalidPostalCodeException, InvalidItemException, ItemAlreadyExistsException {

        given(storeService.existsById(storeId)).willReturn(false);
        given(addressService.isValidPostalCode(store.getAddress().getPostalCode())).willReturn(true);

        assertThat(storeController.add(store).getStatusCode()).isEqualTo(HttpStatus.CREATED);

        then(storeService).should().add(captorStore.capture());

        assertThat(captorStore.getValue()).isEqualTo(store);
    }

    @Test
    void addStoreThrowsInvalidStoreException() {

        assertThrows(InvalidStoreException.class, () -> storeController.add(null));
    }

    @Test
    void addStoreThrowsStoreAlreadyExistsException() {

        given(storeService.existsById(storeId)).willReturn(true);
        
        assertThrows(StoreAlreadyExistsException.class, () -> storeController.add(store));
    }

    @Test
    void addThrowsInvalidPostalCodeException() {

        given(storeService.existsById(storeId)).willReturn(false);
        given(addressService.isValidPostalCode(store.getAddress().getPostalCode())).willReturn(false);

        assertThrows(InvalidPostalCodeException.class, () -> storeController.add(store));
    }

    @Test
    void updateStore() throws InventoryAlreadyExistsException, InvalidStoreException, StoreNotFoundException, InvalidLoginException, LoginNotFoundException, InvalidItemException, ItemNotFoundException {

        given(storeService.existsById(storeId)).willReturn(true);
        given(storeService.getById(storeId)).willReturn(store);

        assertThat(HttpStatus.OK).isEqualTo(storeController.update(store, storeId).getStatusCode());

        then(storeService).should().update(captorStore.capture());

        assertThat(captorStore.getValue()).isEqualTo(store);
    }

    @Test
    void updateStoreThrowsInvalidStoreException() {

        assertThrows(InvalidStoreException.class, () -> storeController.add(null));
    }

    @Test
    void updateStoreThrowsStoreNotFoundException() {

        given(storeService.existsById(storeId)).willReturn(false);

        assertThrows(StoreNotFoundException.class, () -> storeController.update(store, storeId));
    }

    @Test
    void deleteStore() throws StoreNotFoundException, LoginNotFoundException, ItemNotFoundException {

        given(storeService.existsById(storeId)).willReturn(true);
        given(storeService.getById(storeId)).willReturn(store);

        assertThat(HttpStatus.OK).isEqualTo(storeController.delete(storeId).getStatusCode());

        then(storeService).should().remove(captorStore.capture());

        assertThat(captorStore.getValue()).isEqualTo(store);
    }

    @Test
    void deleteStoreThrowsStoreNotFoundException() {

        given(storeService.existsById(storeId)).willReturn(false);

        assertThrows(StoreNotFoundException.class, () -> storeController.delete(storeId));
    }

    @Test
    void list() {

    }

    @Test
    void get() throws InvalidNameException, InvalidStoreException, StoreNotFoundException, InvalidLoginException, LoginNotFoundException, ItemNotFoundException, InvalidItemException {
        
        given(storeService.existsById(storeId)).willReturn(true);
        given(storeService.getById(storeId)).willReturn(store);

        assertThat(HttpStatus.OK).isEqualTo(storeController.get(storeId).getStatusCode());
        assertThat(store).isEqualTo(storeController.get(storeId).getBody());
    }

    @Test
    void getStoreThrowsStoreNotFoundException() {

        given(storeService.existsById(storeId)).willReturn(false);

        assertThrows(StoreNotFoundException.class, () -> storeController.get(storeId));
    }

    @Test
    void getStoreThrowsInvalidStoreException() {

        assertThrows(InvalidStoreException.class, () -> storeController.get(null));
    }
}
