package org.jayhenri.ecommerce.controller;

import org.jayhenri.ecommerce.exception.CustomerNotFoundException;
import org.jayhenri.ecommerce.exception.EmailNotSameException;
import org.jayhenri.ecommerce.exception.InvalidCustomerException;
import org.jayhenri.ecommerce.exception.OrderNotFoundException;
import org.jayhenri.ecommerce.model.*;
import org.jayhenri.ecommerce.service.CustomerService;

import org.jayhenri.ecommerce.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import javax.validation.Valid;
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

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateCustomer(@Valid @RequestBody Customer customer) throws InvalidCustomerException, CustomerNotFoundException, EmailNotSameException {
        customerService.update(customer);
    }

    @DeleteMapping(value = "/delete/{email}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCustomer(@PathVariable String email) throws InvalidCustomerException, CustomerNotFoundException, InvalidNameException {
        customerService.delete(customerService.getByEmail(email));
    }

    @GetMapping(value = "/list/customers")
    public List<Customer> listCustomers() {
        return customerService.findAllCustomers();
    }

    @GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getByEmail(@Valid @PathVariable String email) throws InvalidNameException, CustomerNotFoundException {
        return customerService.getByEmail(email);
    }

    @PutMapping(value = "/{email}/cart/add/{productName}")
    public void addToCart(@PathVariable String productName, @PathVariable String email) throws InvalidNameException, InvalidCustomerException, EmailNotSameException, CustomerNotFoundException {
        customerService.addToCart(customerService.getByEmail(email), inventoryService.getByProductName(productName));
    }

    @PutMapping(value = "/{email}/cart/remove/{productName}")
    public void removeFromCart(@PathVariable String productName, @PathVariable String email) throws InvalidCustomerException, EmailNotSameException, CustomerNotFoundException, InvalidNameException {
        customerService.removeFromCart(customerService.getByEmail(email), inventoryService.getByProductName(productName));
    }

    @PutMapping(value = "/{email}/cart/empty")
    public void emptyCart(@PathVariable String email) throws InvalidNameException, CustomerNotFoundException {
        customerService.emptyCart(customerService.getByEmail(email));
    }

    @PostMapping(value = "/{email}/creditCard/add/")
    public void addCreditCard(@PathVariable String email, @RequestBody CreditCard creditCard) throws InvalidNameException, InvalidCustomerException, EmailNotSameException, CustomerNotFoundException {
        customerService.addCreditCard(customerService.getByEmail(email), creditCard);
    }

    @PutMapping(value = "/{email}/creditCard/update/{fourDigits}")
    public void removeCreditCard(@PathVariable String email, @RequestBody CreditCard creditCard) throws InvalidNameException, CustomerNotFoundException, InvalidCustomerException, EmailNotSameException {
        customerService.removeCreditCard(customerService.getByEmail(email), creditCard);
    }

    @GetMapping(value = "/{email}/creditCards/list")
    public List<CreditCard> listCreditCards(@PathVariable String email) throws InvalidNameException, CustomerNotFoundException {
        return customerService.findAllCreditCards(email);
    }

    @PostMapping(value = "/{email}/orders/add/")
    public void addOrder(@PathVariable String email, @RequestBody Order order) throws InvalidNameException, InvalidCustomerException, EmailNotSameException, CustomerNotFoundException {
        customerService.addOrder(customerService.getByEmail(email), order);
    }

    @PutMapping(value = "/{email}/orders/updateStatus/{orderID}/{status}")
    public void updateOrder(@PathVariable String email, @PathVariable UUID uuid, @PathVariable String orderStatus) throws InvalidNameException, CustomerNotFoundException, OrderNotFoundException {
        customerService.updateOrder(customerService.getByEmail(email), customerService.getOrder(customerService.getByEmail(email), uuid), orderStatus);
    }

    @GetMapping(value = "/{email}/orders/list")
    public List<Order> listOrders(@PathVariable String email) throws InvalidNameException, CustomerNotFoundException {
        return customerService.findAllOrders(email);
    }

//    @GetMapping(value = "/checkout")
//    public void checkout(@RequestBody Cart cart) {
//        customerService.checkout(cart);
//    }
}
