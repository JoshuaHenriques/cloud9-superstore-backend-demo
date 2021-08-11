package org.jayhenri.cloud9.controller.item;

import org.jayhenri.cloud9.exception.invalid.InvalidReviewException;
import org.jayhenri.cloud9.exception.notfound.ReviewNotFoundException;
import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
import org.jayhenri.cloud9.model.review.Review;
import org.jayhenri.cloud9.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import java.util.List;
import java.util.UUID;

/**
 * The type Review controller.
 */
@RestController // Indicates that the data returned by each method will be written straight into
// the response body instead of rendering a template
@RequestMapping("api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final InventoryService inventoryService;

    /**
     * Instantiates a new Review controller.
     *
     * @param reviewService    the review service
     * @param inventoryService the inventory service
     */
    @Autowired
    public ReviewController(ReviewService reviewService, InventoryService inventoryService) {
        this.reviewService = reviewService;
        this.inventoryService = inventoryService;
    }

    /**
     * Register response entity.
     *
     * @param review the review
     * @return the response entity
     * @throws ReviewAlreadyExistsException the review already exists exception
     * @throws InvalidPostalCodeException   the invalid postal code exception
     * @throws InvalidReviewException       the invalid review exception
     */
    @PostMapping(value = "/review", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody Review review)
            throws ReviewAlreadyExistsException, InvalidPostalCodeException, InvalidReviewException {

        if (ObjectUtils.isEmpty(review))
            throw new InvalidReviewException();

        else if (reviewService.existsByPhoneNumber(review.getPhoneNumber())
                || reviewService.existsByEmail(review.getEmail()))
            throw new ReviewAlreadyExistsException();

        else if (!addressService.isValidPostalCode(review.getAddress().getPostalCode()))
            throw new InvalidPostalCodeException();

        reviewService.add(review);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("ReviewRegistrationController", "register");
        return new ResponseEntity<>("Successfully Created Review", responseHeaders, HttpStatus.CREATED);
    }

    /**
     * Update review.
     *
     * @param review the review
     * @return the response entity
     * @throws InvalidReviewException  the invalid review exception
     * @throws ReviewNotFoundException the review not found exception
     */
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateReview(@RequestBody Review review)
            throws InvalidReviewException, ReviewNotFoundException {
        if (!ObjectUtils.isEmpty(review)) {
            if (reviewService.existsByEmail(review.getEmail())) {
                reviewService.update(review);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("ReviewController", "updateReview");
                return new ResponseEntity<>("Successfully Updated Review", responseHeaders, HttpStatus.OK);
            } else
                throw new ReviewNotFoundException();
        } else
            throw new InvalidReviewException();
    }

    /**
     * Delete review.
     *
     * @param email the email
     * @return the response entity
     * @throws ReviewNotFoundException the review not found exception
     */
    @DeleteMapping(value = "/delete/{email}")
    public ResponseEntity<String> deleteReview(@PathVariable String email)
            throws ReviewNotFoundException {
        if (reviewService.existsByEmail(email)) {
            Review _review = reviewService.getByEmail(email);
            reviewService.delete(_review);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("ReviewController", "deleteReview");
            return new ResponseEntity<>("Successfully Deleted Review", responseHeaders, HttpStatus.OK);
        } else
            throw new ReviewNotFoundException();
    }

    /**
     * List reviews response entity.
     *
     * @param pageNo   the page no
     * @param pageSize the page size
     * @return the response entity
     */
    @GetMapping(value = "/list/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Review>> listReviews(@RequestParam(defaultValue = "0") Integer pageNo,
                                                        @RequestParam(defaultValue = "50") Integer pageSize) {
        // @RequestParam(defaultValue = "email") String sortBy
        List<Review> list = reviewService.findAllReviews(pageNo, pageSize); // sortBy

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("ReviewController", "listReviews");
        return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
    }
}
