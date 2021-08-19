package org.jayhenri.cloud9.service.inventory;

import org.jayhenri.cloud9.exception.OutOfStockException;
import org.jayhenri.cloud9.interfaces.service.other.InventoryServiceI;
import org.jayhenri.cloud9.model.inventory.OnlineInventory;
import org.jayhenri.cloud9.model.item.Item;
import org.jayhenri.cloud9.repository.inventory.OnlineInventoryRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * The type Online inventory service.
 */
@Service
public class OnlineInventoryService implements InventoryServiceI<OnlineInventory> {

    private final OnlineInventoryRepository inventoryRepository;

    /**
     * Instantiates a new Online inventory service.
     *
     * @param inventoryRepository the inventory repository
     */
    @Autowired
    public OnlineInventoryService(OnlineInventoryRepository inventoryRepository) {

        this.inventoryRepository = inventoryRepository;
    }

    public void add(Item item, int quantity, double price) {

        OnlineInventory inventory = new OnlineInventory(item, item.getItemName(), quantity, price);
        inventoryRepository.save(inventory);
    }

    public void update(OnlineInventory inventory) {

        inventoryRepository.save(inventory);
    }

    public void delete(OnlineInventory inventory) {

        inventoryRepository.delete(inventory);
    }

    public List<OnlineInventory> findAll() {

        return inventoryRepository.findAll();
    }

    public boolean existsByItemName(String itemName) {

        return inventoryRepository.existsByItemName(itemName);
    }

    public OnlineInventory getByItemName(String itemName) {

        return inventoryRepository.getByItemName(itemName);
    }

    public boolean existsById(UUID uuid) {

        return inventoryRepository.existsById(uuid);
    }

    public OnlineInventory getById(UUID uuid) {

        return inventoryRepository.getById(uuid);
    }

    public boolean canPurchase(@NotNull OnlineInventory onlineInventory, int quantity) {
        return (onlineInventory.getQuantity() - quantity) >= 0;
    }

    public void purchase(OnlineInventory onlineInventory, int quantity) throws OutOfStockException {
        if (canPurchase(onlineInventory, quantity))
            onlineInventory.setQuantity(onlineInventory.getQuantity() - quantity);
        else throw new OutOfStockException();
    }
}
