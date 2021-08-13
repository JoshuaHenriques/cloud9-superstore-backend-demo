package org.jayhenri.cloud9.service.store;

import org.jayhenri.cloud9.model.store.Store;
import org.jayhenri.cloud9.repository.store.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


/**
 * The type Customer service.
 */
@Service
public class StoreService {

    private final StoreRepository storeRepository;
    
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
    public void delete(Store store) {

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
    public List<Store> findAllStores() {
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