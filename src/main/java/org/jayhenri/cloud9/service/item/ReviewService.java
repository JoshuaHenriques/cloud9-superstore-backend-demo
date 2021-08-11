package org.jayhenri.cloud9.service.item;

import org.jayhenri.cloud9.model.item.Item;
import org.jayhenri.cloud9.model.item.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;


/**
 * The type Customer service.
 */
@Service
public class ReviewService {

    private final ItemService itemService;

    /**
     * Instantiates a new Review service.
     *
     * @param itemService the item service
     */
    @Autowired
    public ReviewService(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Add.
     *
     * @param item   the item
     * @param review the review
     */
    public void addReview(Item item, Review review) {

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
     * @param item   the item
     * @param review the review
     */
    public void delete(Item item, Review review) {
        item.getReviews().forEach(review1 -> {
            if (review1.getReviewUUID().equals(review.getReviewUUID()))
                item.getReviews().remove(review);
        });
        itemService.update(item);
    }

    /**
     * Find all customers list.
     *
     * @param item the item
     * @return the list
     */
    public Set<Review> findAllReviews(Item item) {

        return item.getReviews();
    }
}