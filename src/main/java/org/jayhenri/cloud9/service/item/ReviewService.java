package org.jayhenri.cloud9.service.item;

import org.jayhenri.cloud9.interfaces.service.other.ItemServiceI;
import org.jayhenri.cloud9.interfaces.service.other.ReviewServiceI;
import org.jayhenri.cloud9.interfaces.service.ServiceI;
import org.jayhenri.cloud9.model.item.Item;
import org.jayhenri.cloud9.model.item.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;


/**
 * The type Customer service.
 */
@Service
public class ReviewService implements ReviewServiceI {

    private final ItemServiceI itemService;

    /**
     * Instantiates a new Review service.
     *
     * @param itemService the item service
     */
    @Autowired
    public ReviewService(ItemServiceI itemService) {


        this.itemService = itemService;
    }

    /**
     * Add.
     *
     * @param item   the item
     * @param review the review
     */
    public void add(Item item, Review review) {

        item.getReviews().add(review);
        itemService.update(item);
    }

    /**
     * Update.
     *
     * @param item   the item
     * @param review the review
     */
    public void update(Item item, Review review) {

        item.getReviews().forEach(review1 -> {
            if (review1.getReviewUUID().equals(review.getReviewUUID()))
                review1.setText(review.getText());
            review1.setRating(review.getRating());
        });
        itemService.update(item);
    }

    /**
     * Delete.
     *
     * @param item     the item
     * @param reviewId the review id
     */
    public void remove(Item item, UUID reviewId) {
        item.getReviews().forEach(review1 -> {
            if (review1.getReviewUUID().equals(reviewId))
                item.getReviews().remove(review1);
        });
        itemService.update(item);
    }

    /**
     * Find all customers list.
     *
     * @param item the item
     * @return the list
     */
    public Set<Review> findAll(Item item) {

        return item.getReviews();
    }

    /**
     * Exists by email boolean.
     *
     * @param item     the item
     * @param reviewId the review id
     * @return the boolean
     */
    public boolean existsById(Item item, UUID reviewId) {

        AtomicBoolean exists = new AtomicBoolean(false);
        item.getReviews().forEach(review -> {
            if (review.getReviewUUID().equals(reviewId))
                exists.set(true);
            else
                exists.set(false);
        });

        return exists.get();
    }

    /**
     * Gets by id.
     *
     * @param item     the item
     * @param reviewId the review id
     * @return the by id
     */
    public Review getById(Item item, UUID reviewId) {

        AtomicReference<Review> review = new AtomicReference<>(new Review());
        item.getReviews().forEach(review1 -> {
            if (review1.getReviewUUID().equals(reviewId))
                review.set(review1);
        });

        return review.get();
    }
}