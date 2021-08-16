package org.jayhenri.cloud9.item;

import org.jayhenri.cloud9.model.item.Item;
import org.jayhenri.cloud9.model.item.Review;
import org.jayhenri.cloud9.service.item.ItemService;
import org.jayhenri.cloud9.service.item.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    private Review review;

    private Item item;

    private ReviewService reviewService;

    @Mock
    private ItemService itemService;

    @Captor
    private ArgumentCaptor<Item> captorItem;

    @BeforeEach
    void setUp() {

        reviewService = new ReviewService(itemService);

        item = new Item(
                "iPhone 13 Pro",
                "2021 Model",
                new HashSet<>(),
                null
        );

        review = new Review(
                "This iPhone is the best one out!",
                4
        );
    }
    /**
     * Test add.
     */
    @Test
    void add() {

        reviewService.addReview(item, review);

        then(itemService).should().update(captorItem.capture());

        assertThat(item).isEqualTo(captorItem.getValue());
        assertThat(captorItem.getValue().getReviews().contains(review)).isTrue();
    }

    /**
     * Test update.
     */
    @Test
    void update() {

        item.getReviews().add(review);

        review.setText("Updated description");

        reviewService.updateReview(item, review);

        then(itemService).should().update(captorItem.capture());

        assertThat(captorItem.getValue().getReviews().stream().iterator().next().getText()).isEqualTo("Updated description");
    }

    /**
     * Delete.
     */
    @Test
    void delete() {

        item.getReviews().add(review);

        reviewService.deleteReview(item, review.getReviewUUID());

        then(itemService).should().update(captorItem.capture());

        assertThat(captorItem.getValue()).isEqualTo(item);
        assertThat(captorItem.getValue().getReviews().contains(review)).isFalse();
    }

    /**
     * Find all.
     */
    @Test
    void findAllReviews() {

        item.getReviews().add(review);

        assertThat(reviewService.findAllReviews(item).contains(review)).isTrue();
    }

    /**
     * Exists by review name.
     */
    @Test
    void existsById() {

        item.getReviews().add(review);

        assertThat(reviewService.existsById(item, review.getReviewUUID())).isTrue();
    }

    /**
     * Exists by review name.
     */
    @Test
    void getById() {

        item.getReviews().add(review);

        assertThat(reviewService.getById(item, review.getReviewUUID())).isEqualTo(review);
    }
}
