package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.exception.*;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final OrderDBService orderDBService;

    private static final Double HST = 0.13;
    private static final Double DELIVERY_FEE = 9.99;

    private static final String REGEX_POSTAL_CODE = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";

    @Autowired
    public CustomerService(CustomerRepository customerRepository, OrderDBService orderDBService) {
        this.customerRepository = customerRepository;
        this.orderDBService = orderDBService;
    }

    public boolean existsByPhoneNumber(String phoneNumber) {
        return customerRepository.existsByPhoneNumber(phoneNumber);
    }

    public void add(Customer customer) throws CustomerAlreadyExistsException, InvalidPostalCodeException {

        if (existsByPhoneNumber(customer.getPhoneNumber()) || existsByEmail(customer.getEmail()))
            throw new CustomerAlreadyExistsException();

        else if (!isValidPostalCode(customer.getAddress().getPostalCode()))
            throw new InvalidPostalCodeException();
        customerRepository.save(customer);
    }

    public void delete(Customer customer){
                customerRepository.delete(customer);
    }

    public void update(Customer customer) {
                customerRepository.save(customer);
    }

    public List<Customer> findAllCustomers(Integer pageNo, Integer pageSize) { // String sortBy
        Pageable paging = PageRequest.of(pageNo, pageSize); // Sort.by(sortBy).ascending()
        Page<Customer> pagedResult = customerRepository.findAll(paging);

        if(pagedResult.hasContent()) return pagedResult.getContent();
        else return new ArrayList<>();
    }

    public List<CreditCard> findAllCreditCards(String email) {
        return getByEmail(email).getCreditCards();
    }

    public boolean isValidPostalCode(String postalCode) {
        Pattern pattern = Pattern.compile(REGEX_POSTAL_CODE);
        Matcher matcher = pattern.matcher(postalCode);
        return matcher.matches();
    }

    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    public Customer getByEmail(String email) {
        return customerRepository.getByEmail(email);
    }

    public void addToCart(Customer customer, Item item) {
        customer.getCart().getItems().add(item);
        update(customer);
    }

    public void removeFromCart(Customer customer, String productName) {

        ArrayList<Item> removeMe = new ArrayList<>();
// Create a list of values you wish to remove, adding to that list within the loop, then call originalList.removeAll(valuesToRemove) at the end
        customer.getCart().getItems().forEach(item -> {
            if(item.getItemName().equals(productName)) {
                removeMe.add(item);
            }
        });
        customer.getCart().getItems().removeAll(removeMe);
        update(customer);
    }

    public void emptyCart(Customer customer) {
        ArrayList<Item> removeMe = customer.getCart().getItems();
        customer.getCart().getItems().removeAll(removeMe);
        update(customer);
    }

    public ArrayList<Item> getCart(Customer customer) {
        return customer.getCart().getItems();
    }

    public void addCreditCard(Customer customer, CreditCard creditCard) {
        customer.getCreditCards().add(creditCard);
        update(customer);
    }

    public void removeCreditCard(Customer customer, String fourDig)  {
        ArrayList<CreditCard> removeMe = new ArrayList<>();
// Create a list of values you wish to remove, adding to that list within the loop, then call originalList.removeAll(valuesToRemove) at the end
        customer.getCreditCards().forEach(creditCard -> {
            if(creditCard.getFourDig() != null && creditCard.getFourDig().equals(fourDig)) {
                removeMe.add(creditCard);
            }
        });
        customer.getCreditCards().removeAll(removeMe);
        update(customer);
    }

    public void addOrder(Customer customer, Order order) {
        customer.getOrders().add(order);
        update(customer);

        OrderDB orderDB = new OrderDB(
                "PROCESSING",
                customer.getEmail(),
                new ArrayList<>(order.getOrder()),
                order.getTotalPrice(),
                order.getTotalPrice()*HST+DELIVERY_FEE
        );
        orderDBService.addOrderToDB(orderDB);
    }

    public void updateOrder(Customer customer, UUID uuid, String orderStatus) {
        customer.getOrders().forEach(order -> {
            if (order.getUuid().equals(uuid)) order.setOrderStatus(orderStatus);
        });
        update(customer);
    }
    public List<Order> findAllOrders(String email)  {
        return getByEmail(email).getOrders();
    }
}