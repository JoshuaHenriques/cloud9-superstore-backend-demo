package org.jayhenri.cloud9.item;

import org.jayhenri.store_manager.controller.item.ReviewController;
import org.jayhenri.store_manager.exception.invalid.InvalidItemException;
import org.jayhenri.store_manager.exception.invalid.InvalidReviewException;
import org.jayhenri.store_manager.exception.notfound.ItemNotFoundException;
import org.jayhenri.store_manager.exception.notfound.ReviewNotFoundException;
import org.jayhenri.store_manager.interfaces.service.other.ItemServiceI;
import org.jayhenri.store_manager.interfaces.service.other.ReviewServiceI;
import org.jayhenri.store_manager.model.item.Item;
import org.jayhenri.store_manager.model.item.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import javax.naming.InvalidNameException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * The type Review controller test.
 */
@ExtendWith(MockitoExtension.class)
public class ReviewControllerTest {

    private Review review;

    private Item item;

    @Mock
    private ReviewServiceI reviewService;

    @Mock
    private ItemServiceI itemService;

    @Captor
    private ArgumentCaptor<Review> captorReview;

    @Captor
    private ArgumentCaptor<Item> captorItem;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    private ReviewController reviewController;

    private UUID reviewId, itemId;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        reviewId = UUID.randomUUID();
        itemId = UUID.randomUUID();

        reviewController = new ReviewController(reviewService, itemService);

        review = new Review();
        item = new Item();
    }

    /**
     * Add.
     *
     * @throws ItemNotFoundException  the item not found exception
     * @throws InvalidReviewException the invalid review exception
     */
    @Test
    void add() throws ItemNotFoundException, InvalidReviewException {

        given(itemService.existsById(itemId)).willReturn(true);
        given(itemService.getById(itemId)).willReturn(item);

        assertThat(reviewController.add(review, itemId).getStatusCode()).isEqualTo(HttpStatus.CREATED);

        then(reviewService).should().add(captorItem.capture(), captorReview.capture());

        assertThat(captorReview.getValue()).isEqualTo(review);
        assertThat(captorItem.getValue()).isEqualTo(item);
    }

    /**
     * Add throws invalid review exception.
     */
    @Test
    void addThrowsInvalidReviewException() {

        assertThrows(InvalidReviewException.class, () -> reviewController.add(null, null));
    }

    /**
     * Add throws item not found exception.
     */
    @Test
    void addThrowsItemNotFoundException() {

        given(itemService.existsById(itemId)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> reviewController.add(review, itemId));
    }

    /**
     * Update.
     *
     * @throws ItemNotFoundException   the item not found exception
     * @throws ReviewNotFoundException the review not found exception
     * @throws InvalidItemException    the invalid item exception
     * @throws InvalidReviewException  the invalid review exception
     */
    @Test
    void update() throws ItemNotFoundException, ReviewNotFoundException, InvalidItemException, InvalidReviewException {

        given(itemService.existsById(itemId)).willReturn(true);
        given(itemService.getById(itemId)).willReturn(item);
        given(reviewService.existsById(item, reviewId)).willReturn(true);

        assertThat(HttpStatus.OK).isEqualTo(reviewController.update(review, itemId, reviewId).getStatusCode());

        then(reviewService).should().update(captorItem.capture(), captorReview.capture());

        assertThat(captorReview.getValue()).isEqualTo(review);
        assertThat(captorItem.getValue()).isEqualTo(item);
    }

    /**
     * Update throws review not found exception.
     */
    @Test
    void updateThrowsReviewNotFoundException() {

        given(itemService.existsById(itemId)).willReturn(true);
        given(itemService.getById(itemId)).willReturn(item);
        given(reviewService.existsById(item, reviewId)).willReturn(false);

        assertThrows(ReviewNotFoundException.class, () -> reviewController.update(review, itemId, reviewId));
    }

    /**
     * Update throws item not found exception.
     */
    @Test
    void updateThrowsItemNotFoundException() {

        given(itemService.existsById(itemId)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> reviewController.update(review, itemId, reviewId));
    }

    /**
     * Update throws invalid review exception.
     */
    @Test
    void updateThrowsInvalidReviewException() {

        assertThrows(InvalidReviewException.class, () -> reviewController.update(null, itemId, reviewId));
    }

    /**
     * Delete.
     *
     * @throws ReviewNotFoundException the review not found exception
     * @throws ItemNotFoundException   the item not found exception
     */
    @Test
    void delete() throws ReviewNotFoundException, ItemNotFoundException {

        given(itemService.existsById(itemId)).willReturn(true);
        given(itemService.getById(itemId)).willReturn(item);
        given(reviewService.existsById(item, reviewId)).willReturn(true);

        assertThat(HttpStatus.OK).isEqualTo(reviewController.delete(itemId, reviewId).getStatusCode());

        then(reviewService).should().remove(captorItem.capture(), captorUUID.capture());

        assertThat(captorItem.getValue()).isEqualTo(item);
        assertThat(captorUUID.getValue()).isEqualTo(reviewId);
    }

    /**
     * Delete throws review not found exception.
     */
    @Test
    void deleteThrowsReviewNotFoundException() {

        given(itemService.existsById(itemId)).willReturn(true);
        given(itemService.getById(itemId)).willReturn(item);
        given(reviewService.existsById(item, reviewId)).willReturn(false);

        assertThrows(ReviewNotFoundException.class, () -> reviewController.delete(itemId, reviewId));
    }

    /**
     * Delete throws item not found exception.
     */
    @Test
    void deleteThrowsItemNotFoundException() {

        given(itemService.existsById(itemId)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> reviewController.delete(itemId, reviewId));
    }

    // @Test
    // void list() {

    // }

    /**
     * Get.
     *
     * @throws InvalidReviewException  the invalid review exception
     * @throws ReviewNotFoundException the review not found exception
     * @throws InvalidNameException    the invalid name exception
     * @throws ItemNotFoundException   the item not found exception
     */
    @Test
    void get() throws InvalidReviewException, ReviewNotFoundException, InvalidNameException, ItemNotFoundException {

        given(itemService.existsById(itemId)).willReturn(true);
        given(itemService.getById(itemId)).willReturn(item);
        given(reviewService.existsById(item, reviewId)).willReturn(true);

        assertThat(HttpStatus.OK).isEqualTo(reviewController.get(itemId, reviewId).getStatusCode());

        then(reviewService).should().getById(captorItem.capture(), captorUUID.capture());

        assertThat(captorItem.getValue()).isEqualTo(item);
        assertThat(captorUUID.getValue()).isEqualTo(reviewId);
    }

    /**
     * Gets throws review not found exception.
     */
    @Test
    void getThrowsReviewNotFoundException() {

        given(itemService.existsById(itemId)).willReturn(true);
        given(itemService.getById(itemId)).willReturn(item);
        given(reviewService.existsById(item, reviewId)).willReturn(false);

        assertThrows(ReviewNotFoundException.class, () -> reviewController.get(itemId, reviewId));
    }

    /**
     * Gets throws item not found exception.
     */
    @Test
    void getThrowsItemNotFoundException() {

        given(itemService.existsById(itemId)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> reviewController.get(itemId, reviewId));
    }

    /**
     * Gets throws invalid review exception.
     */
    @Test
    void getThrowsInvalidReviewException() {

        given(itemService.existsById(itemId)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> reviewController.get(itemId, reviewId));
    }
}
