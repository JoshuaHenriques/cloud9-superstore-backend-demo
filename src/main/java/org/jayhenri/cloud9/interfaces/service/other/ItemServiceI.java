package org.jayhenri.cloud9.interfaces.service.other;

import org.jayhenri.cloud9.interfaces.service.ServiceI;
import org.jayhenri.cloud9.model.item.Item;

import java.util.UUID;

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
