package org.jayhenri.cloud9.item;

import org.jayhenri.store_manager.interfaces.service.other.ItemServiceI;
import org.jayhenri.store_manager.interfaces.service.other.ReviewServiceI;
import org.jayhenri.store_manager.model.item.Item;
import org.jayhenri.store_manager.model.item.Review;
import org.jayhenri.store_manager.service.item.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;

/**
 * The type Review service test.
 */
@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    private Review review;

    private Item item;

    private ReviewServiceI reviewService;

    @Mock
    private ItemServiceI itemService;

    @Captor
    private ArgumentCaptor<Item> captorItem;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        reviewService = new ReviewService(itemService);

        item = new Item(
                "iPhone 13 Pro",
                "2021 Model",
                new HashSet<>(),
                1129.99,
                null
        );

        review = new Review(
                "This iPhone is the best one out!",
                4
        );
    }

    /**
     * Add.
     */
    @Test
    void add() {

        reviewService.add(item, review);

        then(itemService).should().update(captorItem.capture());

        assertThat(item).isEqualTo(captorItem.getValue());
        assertThat(captorItem.getValue().getReviews().contains(review)).isTrue();
    }

    /**
     * Update.
     */
    @Test
    void update() {

        item.getReviews().add(review);

        review.setText("Updated description");

        reviewService.update(item, review);

        then(itemService).should().update(captorItem.capture());

        assertThat(captorItem.getValue().getReviews().stream().iterator().next().getText()).isEqualTo("Updated description");
    }

    /**
     * Delete.
     */
    @Test
    void delete() {

        item.getReviews().add(review);

        reviewService.remove(item, review.getReviewUUID());

        then(itemService).should().update(captorItem.capture());

        assertThat(captorItem.getValue()).isEqualTo(item);
        assertThat(captorItem.getValue().getReviews().contains(review)).isFalse();
    }

    /**
     * Find all reviews.
     */
    @Test
    void findAllReviews() {

        item.getReviews().add(review);

        assertThat(reviewService.findAll(item).contains(review)).isTrue();
    }

    /**
     * Exists by id.
     */
    @Test
    void existsById() {

        item.getReviews().add(review);

        assertThat(reviewService.existsById(item, review.getReviewUUID())).isTrue();
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {

        item.getReviews().add(review);

        assertThat(reviewService.getById(item, review.getReviewUUID())).isEqualTo(review);
    }
}
