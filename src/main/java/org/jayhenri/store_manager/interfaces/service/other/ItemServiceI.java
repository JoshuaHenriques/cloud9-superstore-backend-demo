package org.jayhenri.store_manager.interfaces.service.other;

import java.util.UUID;

import org.jayhenri.store_manager.interfaces.service.ServiceI;
import org.jayhenri.store_manager.model.item.Item;

/**
 * The interface Item service i.
 */
public interface ItemServiceI extends ServiceI<Item> {

    /**
     * Exists by item name boolean.
     *
     * @param itemName the item name
     * @return the boolean
     */
    boolean existsByItemName(String itemName);

    /**
     * Gets by item name.
     *
     * @param itemName the item name
     * @return the by item name
     */
    Item getByItemName(String itemName);

    boolean existsById(UUID uuid);

    Item getById(UUID uuid);
}
