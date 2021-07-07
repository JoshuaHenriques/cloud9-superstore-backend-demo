package org.jayhenri.ecommerce.service;

import lombok.NoArgsConstructor;
import org.jayhenri.ecommerce.exception.*;
import org.jayhenri.ecommerce.model.*;
import org.jayhenri.ecommerce.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
    private InventoryService inventoryService;

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
        // else if (!isValidPassword()){}
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

    public void update(Customer customer) throws CustomerNotFoundException, InvalidCustomerException, EmailNotSameException {
        if (!ObjectUtils.isEmpty(customer))
            if (existsByEmail(customer.getEmail()))
                customerRepository.save(customer);
             else throw new CustomerNotFoundException(customer.getEmail());
         else throw new InvalidCustomerException();
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
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

    public void addToCart(Customer customer, Item item) throws InvalidNameException, CustomerNotFoundException, InvalidCustomerException, EmailNotSameException {
        customer.getCart().getItems().add(item);
        update(customer);
    }

    public void removeFromCart(Customer customer, String productName) throws InvalidCustomerException, EmailNotSameException, CustomerNotFoundException {

        ArrayList<Item> removeMe = new ArrayList<Item>();
// Create a list of values you wish to remove, adding to that list within the loop, then call originalList.removeAll(valuesToRemove) at the end
        customer.getCart().getItems().forEach(item -> {
            if(item.getItemName().equals(productName)) {
                removeMe.add(item);
            }
        });
        customer.getCart().getItems().removeAll(removeMe);
        update(customer);
    }

    public void emptyCart(Customer customer) throws InvalidCustomerException, EmailNotSameException, CustomerNotFoundException {
        ArrayList<Item> removeMe = customer.getCart().getItems();
        customer.getCart().getItems().removeAll(removeMe);
        update(customer);
    }

    public ArrayList<Item> getCart(Customer customer) {
        return customer.getCart().getItems();
    }

    public void addCreditCard(Customer customer, CreditCard creditCard) throws InvalidCustomerException, EmailNotSameException, CustomerNotFoundException {
        customer.getCreditCards().add(creditCard);
        update(customer);
    }

    public void removeCreditCard(Customer customer, String fourDig) throws InvalidCustomerException, EmailNotSameException, CustomerNotFoundException {
        ArrayList<CreditCard> removeMe = new ArrayList<CreditCard>();
// Create a list of values you wish to remove, adding to that list within the loop, then call originalList.removeAll(valuesToRemove) at the end
        customer.getCreditCards().forEach(creditcard -> {
            if(creditcard.getFourDig() != null && creditcard.getFourDig().equals(fourDig)) {
                removeMe.add(creditcard);
            }
        });
        customer.getCreditCards().removeAll(removeMe);
        update(customer);
    }

    public Order getOrder(Customer customer, UUID uuid) throws OrderNotFoundException {
        return customer.getOrders().stream().filter(o -> o.getUuid().equals(uuid)).findFirst().orElseThrow(OrderNotFoundException::new);
    }

    public void addOrder(Customer customer, Order order) throws InvalidCustomerException, EmailNotSameException, CustomerNotFoundException {
        customer.getOrders().add(order);
        update(customer);

        OrderDB orderDB = new OrderDB(
                "PROCESSING",
                customer.getEmail(),
                new ArrayList<Item>(order.getOrder()),
                order.getTotalPrice(),
                order.getTotalPrice()*HST+DELIVERY_FEE
        );
        orderDBService.addOrderToDB(orderDB);
    }

    public void updateOrder(Customer customer, Order order, String orderStatus) {
        customer.getOrders().remove(order);
        order.setOrderStatus(orderStatus);
        customer.getOrders().add(order);
    }
    public List<Order> findAllOrders(String email) throws InvalidNameException, CustomerNotFoundException {
        return getByEmail(email).getOrders();
    }


    // TODO
//    public CreditCard getCreditCard(Customer customer, UUID uuid) throws OrderNotFoundException {
//        return customer.getCreditCards().stream().filter(o -> o.getUuid().equals(uuid)).findFirst().orElseThrow(OrderNotFoundException::new);
//    }
    /*
    private void encryptPassword(Login login) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(login.getPassword());
        login.setPassword(encodedPassword);

        loginRepository.save(login);
    }
    */
}