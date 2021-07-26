package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.exception.CustomerAlreadyExistsException;
import org.jayhenri.ecommerce.exception.InvalidPostalCodeException;
import org.jayhenri.ecommerce.model.*;
import org.jayhenri.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * The type Customer service.
 */
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    /**
     * Instantiates a new Customer service.
     *
     * @param customerRepository the customer repository
     */
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
        // this.orderDBService = orderDBService;
    }

    /**
     * Exists by phone number boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    public boolean existsByPhoneNumber(String phoneNumber) {

        return customerRepository.existsByPhoneNumber(phoneNumber);
    }

    /**
     * Add.
     *
     * @param customer the customer
     * @throws CustomerAlreadyExistsException the customer already exists exception
     * @throws InvalidPostalCodeException     the invalid postal code exception
     */
    public void add(Customer customer) {

        customerRepository.save(customer);
    }

    /**
     * Delete.
     *
     * @param customer the customer
     */
    public void delete(Customer customer) {

        customerRepository.delete(customer);
    }

    /**
     * Update.
     *
     * @param customer the customer
     */
    public void update(Customer customer) {

        customerRepository.save(customer);
    }

    /**
     * Find all customers list.
     *
     * @param pageNo   the page no
     * @param pageSize the page size
     * @return the list
     */
    public List<Customer> findAllCustomers(Integer pageNo, Integer pageSize) {
        // String sortBy
        Pageable paging = PageRequest.of(pageNo, pageSize); // Sort.by(sortBy).ascending()
        Page<Customer> pagedResult = customerRepository.findAll(paging);

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

        return customerRepository.existsByEmail(email);
    }

    /**
     * Gets by email.
     *
     * @param email the email
     * @return the by email
     */
    public Customer getByEmail(String email) {

        return customerRepository.getByEmail(email);
    }

    /**
     * Add to cart.
     *
     * @param customer the customer
     * @param item     the item
     */
    public void addToCart(Customer customer, Item item) {

        customer.getCart().getItems().add(item);
        update(customer);
    }

    /**
     * Remove from cart.
     *
     * @param customer    the customer
     * @param productName the product name
     */
    public void removeFromCart(Customer customer, String productName) {

        List<Item> removeMe = new ArrayList<>();
// Create a list of values you wish to remove, adding to that list within the loop, then call originalList.removeAll(valuesToRemove) at the end
        customer.getCart().getItems().forEach(item -> {
            if (item.getItemName().equals(productName)) {
                removeMe.add(item);
            }
        });
        customer.getCart().getItems().removeAll(removeMe);
        update(customer);
    }

    /**
     * Empty cart.
     *
     * @param customer the customer
     */
    public void emptyCart(Customer customer) {

        customer.getCart().setItems(new ArrayList<>());
        customer.getCart().setTotal(0.00);
        update(customer);
    }

    /**
     * Gets cart.
     *
     * @param customer the customer
     * @return the cart
     */
    public Cart getCart(Customer customer) {

        return customer.getCart();
    }

    /**
     * Add credit card.
     *
     * @param customer   the customer
     * @param creditCard the credit card
     */
    public void addCreditCard(Customer customer, CreditCard creditCard) {

        customer.getCreditCards().add(creditCard);
        update(customer);
    }

    /**
     * Remove credit card.
     *
     * @param customer the customer
     * @param fourDig  the four dig
     */
    public void removeCreditCard(Customer customer, String fourDig) {

        ArrayList<CreditCard> removeMe = new ArrayList<>();
// Create a list of values you wish to remove, adding to that list within the loop, then call originalList.removeAll(valuesToRemove) at the end
        customer.getCreditCards().forEach(creditCard -> {
            if (creditCard.getFourDig() != null && creditCard.getFourDig().equals(fourDig)) {
                removeMe.add(creditCard);
            }
        });
        customer.getCreditCards().removeAll(removeMe);
        update(customer);
    }

    /**
     * Find all credit cards list.
     *
     * @param customer the email
     * @return the list
     */
    public List<CreditCard> findAllCreditCards(Customer customer) {

        return customer.getCreditCards();
    }


    /**
     * Add order.
     *
     * @param customer     the customer
     * @param orderDetails the order
     */
    public void addOrder(Customer customer, OrderDetails orderDetails) {

        customer.getOrderDetailsList().add(orderDetails);
        update(customer);

        // todo:https://www.youtube.com/watch?v=QH1tsa-_G9Y OrderDB addToDB = new OrderDB(orderDetails);

//        OrderDB orderDB = new OrderDB(
//                "PROCESSING",
//                customer.getEmail(),
//                new ArrayList<>(order.getOrder()),
//                order.getTotalPrice(),
//                order.getTotalPrice()*HST+DELIVERY_FEE
//        );
//        orderDBService.addOrderToDB(orderDB);
    }

    /**
     * Update order.
     *
     * @param customer    the customer
     * @param uuid        the uuid
     * @param orderStatus the order status
     */
    public void updateOrder(Customer customer, UUID uuid, String orderStatus) {

        customer.getOrderDetailsList().forEach(orderDetails -> {
            if (orderDetails.getOrderUUID().equals(uuid)) orderDetails.setOrderStatus(orderStatus);
        });
        update(customer);
    }

    /**
     * Find all orderDetails list.
     *
     * @param customer the email
     * @return the list
     */
    public List<OrderDetails> findAllOrders(Customer customer) {

        return customer.getOrderDetailsList();
    }
}