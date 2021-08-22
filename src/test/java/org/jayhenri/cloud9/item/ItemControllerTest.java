package org.jayhenri.cloud9.item;

import org.jayhenri.cloud9.controller.item.ItemController;
import org.jayhenri.cloud9.exception.alreadyexists.ItemAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidItemException;
import org.jayhenri.cloud9.interfaces.service.other.ItemServiceI;
import org.jayhenri.cloud9.model.item.Item;
import org.jayhenri.cloud9.repository.item.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * The type Item controller test.
 */
@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {

    private Item item;

    @Mock
    private ItemServiceI itemService;

    @Mock
    private ItemRepository itemRepository;

    @Captor
    private ArgumentCaptor<Item> captorItem;

    @Captor
    private ArgumentCaptor<String> captorString;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    private ItemController itemController;

    private UUID uuid;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        uuid = UUID.randomUUID();

        itemController = new ItemController(itemService);

        item = new Item(
                "iPhone 13 Pro",
                "2021 Model",
                new HashSet<>(),
                1299.99,
                null
        );
    }

    /**
     * Add item.
     *
     * @throws ItemAlreadyExistsException the item already exists exception
     * @throws InvalidItemException       the invalid item exception
     */
    @Test
    void addItem() throws ItemAlreadyExistsException, InvalidItemException {

        given(itemService.existsById(item.getItemUUID())).willReturn(false);
        given(itemService.existsByItemName(item.getItemName())).willReturn(false);

        assertThat(itemController.add(item).getStatusCode()).isEqualTo(HttpStatus.CREATED);

        then(itemService).should().add(captorItem.capture());

        assertThat(captorItem.getValue()).isEqualTo(item);
    }
}
