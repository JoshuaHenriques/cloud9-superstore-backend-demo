package org.jayhenri.store_manager.service.item;

import org.jayhenri.store_manager.interfaces.service.other.ItemServiceI;
import org.jayhenri.store_manager.model.item.Item;
import org.jayhenri.store_manager.repository.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


/**
 * The type Item service.
 */
@Service
public class ItemService implements ItemServiceI {

    private final ItemRepository itemRepository;

    /**
     * Instantiates a new Item service.
     *
     * @param itemRepository the item repository
     */
    @Autowired
    public ItemService(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
        // this.orderDBService = orderDBService;
    }

    public void add(Item customer) {

        itemRepository.save(customer);
    }

    public void remove(Item customer) {

        itemRepository.delete(customer);
    }

    public void update(Item customer) {

        itemRepository.save(customer);
    }

    public List<Item> findAll() {

        return itemRepository.findAll();
    }

    public boolean existsByItemName(String itemName) {

        return itemRepository.existsByItemName(itemName);
    }

    public Item getByItemName(String itemName) {

        return itemRepository.getByItemName(itemName);
    }

    public boolean existsById(UUID uuid) {

        return itemRepository.existsById(uuid);
    }

    public Item getById(UUID uuid) {

        return itemRepository.getById(uuid);
    }
}