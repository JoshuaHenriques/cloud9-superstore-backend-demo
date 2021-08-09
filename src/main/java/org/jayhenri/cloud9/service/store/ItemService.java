package org.jayhenri.cloud9.service.store;

import org.jayhenri.cloud9.model.store.Item;
import org.jayhenri.cloud9.repository.store.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Customer service.
 */
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * Instantiates a new Employee service.
     *
     * @param itemRepository the customer repository
     */
    @Autowired
    public ItemService(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
        // this.orderDBService = orderDBService;
    }

    /**
     * Exists by phone number boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    public boolean existsByPhoneNumber(String phoneNumber) {

        return itemRepository.existsByPhoneNumber(phoneNumber);
    }

    /**
     * Add.
     *
     * @param customer the customer
     */
    public void add(Item customer) {

        itemRepository.save(customer);
    }

    /**
     * Delete.
     *
     * @param customer the customer
     */
    public void delete(Item customer) {

        itemRepository.delete(customer);
    }

    /**
     * Update.
     *
     * @param customer the customer
     */
    public void update(Item customer) {

        itemRepository.save(customer);
    }

    /**
     * Find all customers list.
     *
     * @param pageNo   the page no
     * @param pageSize the page size
     * @return the list
     */
    public List<Item> findAllEmployees(Integer pageNo, Integer pageSize) {
        // String sortBy
        Pageable paging = PageRequest.of(pageNo, pageSize); // Sort.by(sortBy).ascending()
        Page<Item> pagedResult = itemRepository.findAll(paging);

        if (pagedResult.hasContent()) return pagedResult.getContent();
        else return new ArrayList<>();
    }

    /**
     * Exists by email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean existsByEmail(String email) {

        return itemRepository.existsByEmail(email);
    }

    /**
     * Gets by email.
     *
     * @param email the email
     * @return the by email
     */
    public Item getByEmail(String email) {

        return itemRepository.getByEmail(email);
    }
}