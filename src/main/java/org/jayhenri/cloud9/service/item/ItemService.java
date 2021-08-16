package org.jayhenri.cloud9.service.item;

import org.jayhenri.cloud9.model.item.Item;
import org.jayhenri.cloud9.repository.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


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
     * @return the list
     */
    public List<Item> findAllItems() {

        return itemRepository.findAll();
    }

    /**
     * Exists by email boolean.
     *
     * @param itemName the item name
     * @return the boolean
     */
    public boolean existsByItemName(String itemName) {

        return itemRepository.existsByItemName(itemName);
    }

    /**
     * Gets by email.
     *
     * @param itemName the item name
     * @return the by email
     */
    public Item getByItemName(String itemName) {

        return itemRepository.getByItemName(itemName);
    }

    /**
     * Exists by email boolean.
     *
     * @param uuid the email
     * @return the boolean
     */
    public boolean existsById(UUID uuid) {

        return itemRepository.existsById(uuid);
    }

    /**
     * Gets by email.
     *
     * @param uuid the email
     * @return the by email
     */
    public Item getById(UUID uuid) {

        return itemRepository.getById(uuid);
    }
}