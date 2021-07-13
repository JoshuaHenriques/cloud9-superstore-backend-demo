package org.jayhenri.ecommerce.controller;

import org.jayhenri.ecommerce.exception.CustomerNotFoundException;
import org.jayhenri.ecommerce.exception.EmailNotSameException;
import org.jayhenri.ecommerce.exception.InvalidCustomerException;
import org.jayhenri.ecommerce.exception.OrderNotFoundException;
import org.jayhenri.ecommerce.model.*;
import org.jayhenri.ecommerce.service.CustomerService;

import org.jayhenri.ecommerce.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController // Indicates that the data returned by each method will be written straight into the response body instead of rendering a template
@RequestMapping("api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private InventoryService inventoryService;

    private Customer customer;
    private Item item;
    private Order order;
    private CreditCard creditCard;

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateCustomer(@Valid @RequestBody Customer customer) throws InvalidCustomerException, CustomerNotFoundException, EmailNotSameException {
        customerService.update(customer);
    }

    @DeleteMapping(value = "/delete/{email}")
    public void deleteCustomer(@PathVariable String email) throws InvalidCustomerException, CustomerNotFoundException, InvalidNameException {
        customerService.delete(customerService.getByEmail(email));
    }

    @GetMapping(value = "/list/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> listCustomers(@RequestParam(defaultValue = "0") Integer pageNo,
                                                        @RequestParam(defaultValue = "50") Integer pageSize,
                                                        @RequestParam(defaultValue = "email") String sortBy) {
        List<Customer> list = customerService.findAllCustomers(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Customer>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getByEmail(@Valid @PathVariable String email) throws InvalidNameException, CustomerNotFoundException {
        return customerService.getByEmail(email);
    }

    @PostMapping(value = "/{email}/cart/add/{productName}")
    public void addToCart(@PathVariable String productName, @PathVariable String email) throws InvalidNameException, InvalidCustomerException, EmailNotSameException, CustomerNotFoundException {
        customerService.addToCart(customerService.getByEmail(email), inventoryService.getByProductName(productName));
    }

    @DeleteMapping(value = "/{email}/cart/remove/{productName}")
    public void removeFromCart(@PathVariable String productName, @PathVariable String email) throws InvalidCustomerException, EmailNotSameException, CustomerNotFoundException, InvalidNameException {
        customerService.removeFromCart(customerService.getByEmail(email), productName);
    }

    @PatchMapping(value = "/{email}/cart/empty")
    public void emptyCart(@PathVariable String email) throws InvalidNameException, CustomerNotFoundException, InvalidCustomerException, EmailNotSameException {
        customerService.emptyCart(customerService.getByEmail(email));
    }

    @GetMapping(value = "/{email}/cart/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Item> getCart(@PathVariable String email) throws InvalidNameException, CustomerNotFoundException {
        return customerService.getCart(customerService.getByEmail(email));
    }

    // TODO: Github readme: describe functionalities
    @PostMapping(value = "/{email}/creditCard/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCreditCard(@PathVariable String email, @RequestBody CreditCard creditCard) throws InvalidNameException, InvalidCustomerException, EmailNotSameException, CustomerNotFoundException {
        customerService.addCreditCard(customerService.getByEmail(email), creditCard);
    }

    @DeleteMapping(value = "/{email}/creditCard/remove/{fourDigits}")
    public void removeCreditCard(@PathVariable String email, @PathVariable String fourDigits) throws InvalidNameException, CustomerNotFoundException, InvalidCustomerException, EmailNotSameException {
        customerService.removeCreditCard(customerService.getByEmail(email), fourDigits);
    }

    @GetMapping(value = "/{email}/creditCards/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CreditCard> listCreditCards(@PathVariable String email) throws InvalidNameException, CustomerNotFoundException {
        return customerService.findAllCreditCards(email);
    }

    @PostMapping(value = "/{email}/orders/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addOrder(@PathVariable String email, @RequestBody Order order) throws InvalidNameException, InvalidCustomerException, EmailNotSameException, CustomerNotFoundException {
        customerService.addOrder(customerService.getByEmail(email), order);
    }

    @PutMapping(value = "/{email}/orders/updateStatus/{uuid}/{status}")
    public void updateOrder(@PathVariable String email, @PathVariable UUID uuid, @PathVariable String status) throws InvalidCustomerException, EmailNotSameException, CustomerNotFoundException, InvalidNameException, CustomerNotFoundException, OrderNotFoundException {
        customerService.updateOrder(customerService.getByEmail(email), uuid, status);
    }

    @GetMapping(value = "/{email}/orders/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> listOrders(@PathVariable String email) throws InvalidNameException, CustomerNotFoundException {
        return customerService.findAllOrders(email);
    }

//    @GetMapping(value = "/checkout")
//    public void checkout(@RequestBody Cart cart) {
//        customerService.checkout(cart);
//    }
}
