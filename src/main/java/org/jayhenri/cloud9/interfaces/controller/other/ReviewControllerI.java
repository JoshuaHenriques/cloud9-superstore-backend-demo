package org.jayhenri.cloud9.interfaces.controller.other;

import org.jayhenri.cloud9.exception.alreadyexists.ItemAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidItemException;
import org.jayhenri.cloud9.exception.invalid.InvalidReviewException;
import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
import org.jayhenri.cloud9.exception.notfound.ReviewNotFoundException;
import org.jayhenri.cloud9.model.item.Review;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import java.util.Set;
import java.util.UUID;

public interface ReviewControllerI {

    @PostMapping(value = "/add/{itemId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> add(@RequestBody Review review, @PathVariable UUID itemId)
            throws ItemAlreadyExistsException, InvalidItemException, ItemNotFoundException;

    @PutMapping(value = "/update/{itemId}/{reviewId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> update(@RequestBody Review review, @PathVariable UUID itemId, @PathVariable UUID reviewId)
            throws InvalidItemException, ItemNotFoundException, ReviewNotFoundException;

    @DeleteMapping(value = "/delete/{itemId}/{reviewId}")
    ResponseEntity<String> delete(@PathVariable UUID itemId, @PathVariable UUID reviewId)
            throws ItemNotFoundException, ReviewNotFoundException;

    @GetMapping(value = "/list/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Set<Review>> list(@PathVariable UUID itemId);

    @GetMapping(value = "/get//{itemId}/{reviewId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Review> get(@PathVariable UUID itemId, @PathVariable UUID reviewId)
            throws InvalidNameException, ReviewNotFoundException, InvalidReviewException;
}
