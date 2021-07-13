package org.jayhenri.ecommerce.service;

import lombok.NoArgsConstructor;
import org.jayhenri.ecommerce.exception.*;
import org.jayhenri.ecommerce.model.*;
import org.jayhenri.ecommerce.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.naming.InvalidNameException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderDBService orderDBService;

    private static final Double HST = 0.13;
    private static final Double DELIVERY_FEE = 9.99;

    private static final String REGEX_POSTAL_CODE = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";

    private boolean existsByPhoneNumber(String phoneNumber) {
        return customerRepository.existsPhoneNumber(phoneNumber);
    }

    public void add(Customer customer) throws CustomerAlreadyExistsException, InvalidPostalCodeException {

        if (existsByPhoneNumber(customer.getPhoneNumber()) || existsByEmail(customer.getEmail()))
            throw new CustomerAlreadyExistsException();

        else if (!isValidPostalCode(customer.getAddress().getPostalCode()))
            throw new InvalidPostalCodeException();
        //customer.setPassword(encryptPassword(customer.getPassword()));
        customerRepository.save(customer);
    }

    public void delete(Customer customer) throws CustomerNotFoundException, InvalidCustomerException {
        if (!ObjectUtils.isEmpty(customer.getEmail()))
            if (existsByEmail(customer.getEmail())) {
                customerRepository.delete(customer);
            }
            else throw new CustomerNotFoundException();
        else throw new InvalidCustomerException();
    }

    public void update(Customer customer) throws CustomerNotFoundException, InvalidCustomerException {
        if (!ObjectUtils.isEmpty(customer))
            if (existsByEmail(customer.getEmail()))
                customerRepository.save(customer);
             else throw new CustomerNotFoundException(customer.getEmail());
         else throw new InvalidCustomerException();
    }

    public List<Customer> findAllCustomers(Integer pageNo, Integer pageSize) { // String sortBy
        Pageable paging = PageRequest.of(pageNo, pageSize); // Sort.by(sortBy).ascending()
        Page<Customer> pagedResult = customerRepository.findAll(paging);

        if(pagedResult.hasContent()) return pagedResult.getContent();
        else return new ArrayList<>();
    }

    public List<CreditCard> findAllCreditCards(String email) throws InvalidNameException, CustomerNotFoundException {
        return getByEmail(email).getCreditCards();
    }

    private boolean isValidPostalCode(String postalCode) {
        Pattern pattern = Pattern.compile(REGEX_POSTAL_CODE);
        Matcher matcher = pattern.matcher(postalCode);
        return matcher.matches();
    }

    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    public Customer getByEmail(String email) throws InvalidNameException, CustomerNotFoundException {
        if(!ObjectUtils.isEmpty(email)) {
            if (existsByEmail(email)) {
                return customerRepository.getByEmail(email);
            } else throw new CustomerNotFoundException();
        } else throw new InvalidNameException();
    }

    public void addToCart(Customer customer, Item item) throws CustomerNotFoundException, InvalidCustomerException {
        customer.getCart().getItems().add(item);
        update(customer);
    }

    public void removeFromCart(Customer customer, String productName) throws InvalidCustomerException, CustomerNotFoundException {

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

    public void emptyCart(Customer customer) throws InvalidCustomerException, CustomerNotFoundException {
        ArrayList<Item> removeMe = customer.getCart().getItems();
        customer.getCart().getItems().removeAll(removeMe);
        update(customer);
    }

    public ArrayList<Item> getCart(Customer customer) {
        return customer.getCart().getItems();
    }

    public void addCreditCard(Customer customer, CreditCard creditCard) throws InvalidCustomerException, CustomerNotFoundException {
        customer.getCreditCards().add(creditCard);
        update(customer);
    }

    public void removeCreditCard(Customer customer, String fourDig) throws InvalidCustomerException, CustomerNotFoundException {
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

    // Not Used
//    public Order getOrder(Customer customer, UUID uuid) throws OrderNotFoundException {
//        return customer.getOrders().stream().filter(o -> o.getUuid().equals(uuid)).findFirst().orElseThrow(OrderNotFoundException::new);
//    }

    public void addOrder(Customer customer, Order order) throws InvalidCustomerException, CustomerNotFoundException {
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

    public void updateOrder(Customer customer, UUID uuid, String orderStatus) throws InvalidCustomerException, CustomerNotFoundException{
        customer.getOrders().forEach(order -> {
            if (order.getUuid().equals(uuid)) order.setOrderStatus(orderStatus);
        });
        update(customer);
    }
    public List<Order> findAllOrders(String email) throws InvalidNameException, CustomerNotFoundException {
        return getByEmail(email).getOrders();
    }
    
    // private String encryptPassword(String password) {
    //     BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //     return passwordEncoder.encode(password);
    // }
}