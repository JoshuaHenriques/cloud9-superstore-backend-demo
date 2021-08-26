package org.jayhenri.cloud9.controller.item;

import java.util.Set;
import java.util.UUID;

import javax.naming.InvalidNameException;

import org.jayhenri.cloud9.exception.alreadyexists.ItemAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidItemException;
import org.jayhenri.cloud9.exception.invalid.InvalidReviewException;
import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
import org.jayhenri.cloud9.exception.notfound.ReviewNotFoundException;
import org.jayhenri.cloud9.interfaces.controller.other.ReviewControllerI;
import org.jayhenri.cloud9.interfaces.service.other.ItemServiceI;
import org.jayhenri.cloud9.interfaces.service.other.ReviewServiceI;
import org.jayhenri.cloud9.model.item.Item;
import org.jayhenri.cloud9.model.item.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @throws ItemAlreadyExistsException the item already exists exception
     * @throws InvalidItemException       the invalid item exception
     * @throws ItemNotFoundException      the item not found exception
     */
    @PostMapping(value = "/add/{itemId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@RequestBody Review review, @PathVariable UUID itemId)
            throws ItemAlreadyExistsException, InvalidItemException, ItemNotFoundException {

        if (ObjectUtils.isEmpty(review)) {
            if (itemService.existsById(itemId)) {
                Item item = itemService.getById(itemId);
                reviewService.add(item, review);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("ReviewController", "add");
                return new ResponseEntity<>("Successfully Created Item Review", responseHeaders, HttpStatus.CREATED);
            } else
                throw new ItemNotFoundException();
        } else
            throw new InvalidItemException();
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
     */
    @PutMapping(value = "/update/{itemId}/{reviewId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody Review review, @PathVariable UUID itemId, @PathVariable UUID reviewId)
            throws InvalidItemException, ItemNotFoundException, ReviewNotFoundException {
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
            throw new InvalidItemException();
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
     * @throws InvalidNameException the invalid name exception
     */
    @GetMapping(value = "/get//{itemId}/{reviewId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Review> get(@PathVariable UUID itemId, @PathVariable UUID reviewId)
            throws InvalidNameException, ReviewNotFoundException, InvalidReviewException {
        if (!ObjectUtils.isEmpty(reviewId)) {
            if (reviewService.existsById(itemService.getById(itemId), reviewId)) {
                Review _review = reviewService.getById(itemService.getById(itemId), reviewId);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("ReviewController", "get");
                return new ResponseEntity<>(_review, responseHeaders, HttpStatus.OK);
            } else
                throw new ReviewNotFoundException();
        } else
            throw new InvalidReviewException();
    }
}
