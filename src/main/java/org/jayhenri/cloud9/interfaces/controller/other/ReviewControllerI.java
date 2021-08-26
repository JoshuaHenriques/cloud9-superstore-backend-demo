package org.jayhenri.cloud9.interfaces.controller.other;

import java.util.Set;
import java.util.UUID;

import javax.naming.InvalidNameException;

import org.jayhenri.cloud9.exception.alreadyexists.ItemAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidItemException;
import org.jayhenri.cloud9.exception.invalid.InvalidReviewException;
import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
import org.jayhenri.cloud9.exception.notfound.ReviewNotFoundException;
import org.jayhenri.cloud9.model.item.Review;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * The interface Review controller i.
 */
public interface ReviewControllerI {

    /**
     * Add response entity.
     *
     * @param review the review
     * @param itemId the item id
     * @return the response entity
     * @throws ItemAlreadyExistsException the item already exists exception
     * @throws InvalidItemException       the invalid item exception
     * @throws ItemNotFoundException      the item not found exception
     */
    @PostMapping(value = "/add/{itemId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> add(@RequestBody Review review, @PathVariable UUID itemId)
            throws ItemAlreadyExistsException, InvalidItemException, ItemNotFoundException;

    /**
     * Update response entity.
     *
     * @param review   the review
     * @param itemId   the item id
     * @param reviewId the review id
     * @return the response entity
     * @throws InvalidItemException    the invalid item exception
     * @throws ItemNotFoundException   the item not found exception
     * @throws ReviewNotFoundException the review not found exception
     */
    @PutMapping(value = "/update/{itemId}/{reviewId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> update(@RequestBody Review review, @PathVariable UUID itemId, @PathVariable UUID reviewId)
            throws InvalidItemException, ItemNotFoundException, ReviewNotFoundException;

    /**
     * Delete response entity.
     *
     * @param itemId   the item id
     * @param reviewId the review id
     * @return the response entity
     * @throws ItemNotFoundException   the item not found exception
     * @throws ReviewNotFoundException the review not found exception
     */
    @DeleteMapping(value = "/delete/{itemId}/{reviewId}")
    ResponseEntity<String> delete(@PathVariable UUID itemId, @PathVariable UUID reviewId)
            throws ItemNotFoundException, ReviewNotFoundException;

    /**
     * List response entity.
     *
     * @param itemId the item id
     * @return the response entity
     */
    @GetMapping(value = "/list/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Set<Review>> list(@PathVariable UUID itemId);

    /**
     * Get response entity.
     *
     * @param itemId   the item id
     * @param reviewId the review id
     * @return the response entity
     * @throws InvalidNameException    the invalid name exception
     * @throws ReviewNotFoundException the review not found exception
     * @throws InvalidReviewException  the invalid review exception
     */
    @GetMapping(value = "/get//{itemId}/{reviewId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Review> get(@PathVariable UUID itemId, @PathVariable UUID reviewId)
            throws InvalidNameException, ReviewNotFoundException, InvalidReviewException;
}
