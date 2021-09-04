package org.jayhenri.store_manager.service.item;

import org.jayhenri.store_manager.interfaces.service.other.ItemServiceI;
import org.jayhenri.store_manager.interfaces.service.other.ReviewServiceI;
import org.jayhenri.store_manager.model.item.Item;
import org.jayhenri.store_manager.model.item.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;


/**
 * The type Review service.
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

    public void add(Item item, Review review) {

        item.getReviews().add(review);
        itemService.update(item);
    }

    public void update(Item item, Review review) {

        item.getReviews().forEach(review1 -> {
            if (review1.getReviewUUID().equals(review.getReviewUUID()))
                review1.setText(review.getText());
            review1.setRating(review.getRating());
        });
        itemService.update(item);
    }

    public void remove(Item item, UUID reviewId) {
        item.getReviews().forEach(review1 -> {
            if (review1.getReviewUUID().equals(reviewId))
                item.getReviews().remove(review1);
        });
        itemService.update(item);
    }

    public Set<Review> findAll(Item item) {

        return item.getReviews();
    }

    public boolean existsById(Item item, UUID reviewId) {

        AtomicBoolean exists = new AtomicBoolean(false);
        item.getReviews().forEach(review -> exists.set(review.getReviewUUID().equals(reviewId)));

        return exists.get();
    }

    public Review getById(Item item, UUID reviewId) {

        AtomicReference<Review> review = new AtomicReference<>(new Review());
        item.getReviews().forEach(review1 -> {
            if (review1.getReviewUUID().equals(reviewId))
                review.set(review1);
        });

        return review.get();
    }
}