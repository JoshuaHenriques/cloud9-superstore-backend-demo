package org.jayhenri.cloud9.service.store;

import java.util.List;
import java.util.UUID;

import org.jayhenri.cloud9.interfaces.service.ServiceI;
import org.jayhenri.cloud9.model.store.Store;
import org.jayhenri.cloud9.repository.store.StoreRepository;
import org.springframework.stereotype.Service;


/**
 * The type Customer service.
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

    /**
     * Add.
     *
     * @param store the store
     */
    public void add(Store store) {

        storeRepository.save(store);
    }

    /**
     * Delete.
     *
     * @param store the store
     */
    public void remove(Store store) {

        storeRepository.delete(store);
    }

    /**
     * Update.
     *
     * @param store the store
     */
    public void update(Store store) {

        storeRepository.save(store);
    }

    /**
     * Find all stores list.
     *
     * @return the list
     */
    public List<Store> findAll() {

        return storeRepository.findAll();
    }

    /**
     * Exists by email boolean.
     *
     * @param uuid the email
     * @return the boolean
     */
    public boolean existsById(UUID uuid) {

        return storeRepository.existsById(uuid);
    }

    /**
     * Gets by email.
     *
     * @param uuid the email
     * @return the by email
     */
    public Store getById(UUID uuid) {

        return storeRepository.getById(uuid);
    }
}