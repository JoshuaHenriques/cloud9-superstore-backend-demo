package org.jayhenri.store_manager.controller.item;

import org.jayhenri.store_manager.exception.alreadyexists.InventoryAlreadyExistsException;
import org.jayhenri.store_manager.exception.invalid.InvalidItemException;
import org.jayhenri.store_manager.exception.invalid.InvalidReviewException;
import org.jayhenri.store_manager.exception.notfound.ItemNotFoundException;
import org.jayhenri.store_manager.exception.notfound.ReviewNotFoundException;
import org.jayhenri.store_manager.interfaces.controller.other.ReviewControllerI;
import org.jayhenri.store_manager.interfaces.service.other.ItemServiceI;
import org.jayhenri.store_manager.interfaces.service.other.ReviewServiceI;
import org.jayhenri.store_manager.model.item.Item;
import org.jayhenri.store_manager.model.item.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import java.util.Set;
import java.util.UUID;

/**
 * The type Review controller.
 */
@RestController // Indicates that the data returned by each method will be written straight into
// the response body instead of rendering a template
@RequestMapping("api/review")
public class ReviewController implements ReviewControllerI {

    private final ReviewServiceI reviewService;
    private final ItemServiceI itemService;

    /**
     * Instantiates a new Review controller.
     *
     * @param reviewService the review service
     * @param itemService   the item service
     */
    @Autowired
    public ReviewController(ReviewServiceI reviewService, ItemServiceI itemService) {

        this.reviewService = reviewService;
        this.itemService = itemService;
    }

    /**
     * Register response entity.
     *
     * @param review the review
     * @param itemId the item id
     * @return the response entity
     * @throws InventoryAlreadyExistsException the item already exists exception
     * @throws InvalidItemException            the invalid item exception
     * @throws ItemNotFoundException           the item not found exception
     */
    @PostMapping(value = "/add/{itemId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@RequestBody Review review, @PathVariable UUID itemId)
            throws InvalidReviewException, ItemNotFoundException {

        if (!ObjectUtils.isEmpty(review)) {
            if (itemService.existsById(itemId)) {
                Item item = itemService.getById(itemId);
                reviewService.add(item, review);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("ReviewController", "add");
                return new ResponseEntity<>("Successfully Created Item Review", responseHeaders, HttpStatus.CREATED);
            } else
                throw new ItemNotFoundException();
        } else
            throw new InvalidReviewException();
    }

    /**
     * Update item.
     *
     * @param review   the review
     * @param itemId   the item id
     * @param reviewId the review id
     * @return the response entity
     * @throws InvalidItemException    the invalid item exception
     * @throws ItemNotFoundException   the item not found exception
     * @throws ReviewNotFoundException the review not found exception
     * @throws InvalidReviewException
     */
    @PutMapping(value = "/update/{itemId}/{reviewId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody Review review, @PathVariable UUID itemId,
                                         @PathVariable UUID reviewId)
            throws InvalidItemException, ItemNotFoundException, ReviewNotFoundException, InvalidReviewException {
        if (!ObjectUtils.isEmpty(review)) {
            if (itemService.existsById(itemId)) {
                if (reviewService.existsById(itemService.getById(itemId), reviewId)) {

                    review.setReviewUUID(reviewId);
                    reviewService.update(itemService.getById(itemId), review);

                    HttpHeaders responseHeaders = new HttpHeaders();
                    responseHeaders.set("ReviewController", "update");
                    return new ResponseEntity<>("Successfully Updated Item Review", responseHeaders, HttpStatus.OK);
                } else
                    throw new ReviewNotFoundException();
            } else
                throw new ItemNotFoundException();
        } else
            throw new InvalidReviewException();
    }

    /**
     * Delete item.
     *
     * @param itemId   the item id
     * @param reviewId the review id
     * @return the response entity
     * @throws ItemNotFoundException   the item not found exception
     * @throws ReviewNotFoundException the review not found exception
     */
    @DeleteMapping(value = "/delete/{itemId}/{reviewId}")
    public ResponseEntity<String> delete(@PathVariable UUID itemId, @PathVariable UUID reviewId)
            throws ItemNotFoundException, ReviewNotFoundException {
        if (itemService.existsById(itemId)) {
            if (reviewService.existsById(itemService.getById(itemId), reviewId)) {

                reviewService.remove(itemService.getById(itemId), reviewId);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("ReviewController", "deleteItemReview");
                return new ResponseEntity<>("Successfully Deleted Item Review", responseHeaders, HttpStatus.OK);
            } else
                throw new ReviewNotFoundException();
        } else
            throw new ItemNotFoundException();
    }

    /**
     * List items response entity.
     *
     * @param itemId the item id
     * @return the response entity
     */
    @GetMapping(value = "/list/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Review>> list(@PathVariable UUID itemId) {
        Set<Review> list = reviewService.findAll(itemService.getById(itemId));

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("ReviewController", "listItemReview");
        return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
    }

    /**
     * Gets by email.
     *
     * @return the by email
     * @throws InvalidNameException  the invalid name exception
     * @throws ItemNotFoundException
     */
    @GetMapping(value = "/get//{itemId}/{reviewId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Review> get(@PathVariable UUID itemId, @PathVariable UUID reviewId)
            throws InvalidNameException, ReviewNotFoundException, InvalidReviewException, ItemNotFoundException {
        if (!ObjectUtils.isEmpty(reviewId)) {
            if (itemService.existsById(itemId)) {
                if (reviewService.existsById(itemService.getById(itemId), reviewId)) {
                    Review _review = reviewService.getById(itemService.getById(itemId), reviewId);

                    HttpHeaders responseHeaders = new HttpHeaders();
                    responseHeaders.set("ReviewController", "get");
                    return new ResponseEntity<>(_review, responseHeaders, HttpStatus.OK);
                } else
                    throw new ReviewNotFoundException();
            } else
                throw new ItemNotFoundException();
        } else
            throw new InvalidReviewException();
    }
}
