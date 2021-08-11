package org.jayhenri.cloud9.service.item;

import org.jayhenri.cloud9.model.item.Review;
import org.jayhenri.cloud9.repository.item.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Customer service.
 */
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    /**
     * Instantiates a new Review service.
     *
     * @param reviewRepository the customer repository
     */
    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {

        this.reviewRepository = reviewRepository;
        // this.orderDBService = orderDBService;
    }

    /**
     * Exists by phone number boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    public boolean existsByPhoneNumber(String phoneNumber) {

        return reviewRepository.existsByPhoneNumber(phoneNumber);
    }

    /**
     * Add.
     *
     * @param customer the customer
     */
    public void add(Review customer) {

        reviewRepository.save(customer);
    }

    /**
     * Delete.
     *
     * @param customer the customer
     */
    public void delete(Review customer) {

        reviewRepository.delete(customer);
    }

    /**
     * Update.
     *
     * @param customer the customer
     */
    public void update(Review customer) {

        reviewRepository.save(customer);
    }

    /**
     * Find all customers list.
     *
     * @param pageNo   the page no
     * @param pageSize the page size
     * @return the list
     */
    public List<Review> findAllReviews(Integer pageNo, Integer pageSize) {
        // String sortBy
        Pageable paging = PageRequest.of(pageNo, pageSize); // Sort.by(sortBy).ascending()
        Page<Review> pagedResult = reviewRepository.findAll(paging);

        if (pagedResult.hasContent()) return pagedResult.getContent();
        else return new ArrayList<>();
    }

    /**
     * Exists by email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean existsByEmail(String email) {

        return reviewRepository.existsByEmail(email);
    }

    /**
     * Gets by email.
     *
     * @param email the email
     * @return the by email
     */
    public Review getByEmail(String email) {

        return reviewRepository.getByEmail(email);
    }
}