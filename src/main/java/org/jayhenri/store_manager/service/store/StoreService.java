package org.jayhenri.store_manager.service.store;

import org.jayhenri.store_manager.interfaces.service.ServiceI;
import org.jayhenri.store_manager.model.store.Store;
import org.jayhenri.store_manager.repository.store.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


/**
 * The type Store service.
 */
@Service
public class StoreService implements ServiceI<Store> {

    private final StoreRepository storeRepository;

    /**
     * Instantiates a new Store service.
     *
     * @param storeRepository the store repository
     */
    public StoreService(StoreRepository storeRepository) {

        this.storeRepository = storeRepository;
    }

    public void add(Store store) {

        storeRepository.save(store);
    }

    public void remove(Store store) {

        storeRepository.delete(store);
    }

    public void update(Store store) {

        storeRepository.save(store);
    }

    public List<Store> findAll() {

        return storeRepository.findAll();
    }

    public boolean existsById(UUID uuid) {

        return storeRepository.existsById(uuid);
    }

    public Store getById(UUID uuid) {

        return storeRepository.getById(uuid);
    }
}