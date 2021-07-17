package org.jayhenri.ecommerce.controller;

import org.jayhenri.ecommerce.exception.CustomerNotFoundException;
import org.jayhenri.ecommerce.exception.InvalidCustomerException;
import org.jayhenri.ecommerce.exception.ItemNotFoundException;
import org.jayhenri.ecommerce.model.*;
import org.jayhenri.ecommerce.service.CustomerService;

import org.jayhenri.ecommerce.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * The type Customer controller.
 */
@RestController // Indicates that the data returned by each method will be written straight into the response body instead of rendering a template
@RequestMapping("api/customers")
public class CustomerController {


    private final CustomerService customerService;
    private final InventoryService inventoryService;

    /**
     * Instantiates a new Customer controller.
     *
     * @param customerService  the customer service
     * @param inventoryService the inventory service
     */
    @Autowired
    public CustomerController(CustomerService customerService, InventoryService inventoryService) {
        this.customerService = customerService;
        this.inventoryService = inventoryService;
    }

    /**
     * Update customer.
     *
     * @param customer the customer
     * @throws InvalidCustomerException  the invalid customer exception
     * @throws CustomerNotFoundException the customer not found exception
     */
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateCustomer(@Valid @RequestBody Customer customer) throws InvalidCustomerException, CustomerNotFoundException {
        if (!ObjectUtils.isEmpty(customer))
            if (customerService.existsByEmail(customer.getEmail()))
                customerService.update(customer);
            else throw new CustomerNotFoundException(customer.getEmail());
        else throw new InvalidCustomerException();
    }

    /**
     * Delete customer.
     *
     * @param email the email
     * @throws InvalidCustomerException  the invalid customer exception
     * @throws CustomerNotFoundException the customer not found exception
     * @throws InvalidNameException      the invalid name exception
     */
    @DeleteMapping(value = "/delete/{email}")
    public void deleteCustomer(@PathVariable String email) throws InvalidCustomerException, CustomerNotFoundException, InvalidNameException {
        if (!ObjectUtils.isEmpty(email)) {
            if (customerService.existsByEmail(email)) {
                customerService.delete(customerService.getByEmail(email));
            } else throw new CustomerNotFoundException();
        } else throw new InvalidCustomerException();
    }

    /**
     * List customers response entity.
     *
     * @param pageNo   the page no
     * @param pageSize the page size
     * @return the response entity
     */
    @GetMapping(value = "/list/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> listCustomers(@RequestParam(defaultValue = "0") Integer pageNo,
                                                        @RequestParam(defaultValue = "50") Integer pageSize) {
                                                        // @RequestParam(defaultValue = "email") String sortBy
        List<Customer> list = customerService.findAllCustomers(pageNo, pageSize); // sortBy

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Gets by email.
     *
     * @param email the email
     * @return the by email
     * @throws InvalidNameException      the invalid name exception
     * @throws CustomerNotFoundException the customer not found exception
     */
    @GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getByEmail(@Valid @PathVariable String email) throws InvalidNameException, CustomerNotFoundException {
        if(!ObjectUtils.isEmpty(email)) {
            if (customerService.existsByEmail(email)) {
                return customerService.getByEmail(email);
            } else throw new CustomerNotFoundException();
        } else throw new InvalidNameException();
    }

    /**
     * Add to cart.
     *
     * @param productName the product name
     * @param email       the email
     * @throws CustomerNotFoundException the customer not found exception
     * @throws ItemNotFoundException     the item not found exception
     */
    @PostMapping(value = "/{email}/cart/add/{productName}")
    public void addToCart(@PathVariable String productName, @PathVariable String email) throws CustomerNotFoundException, ItemNotFoundException {
        if (customerService.existsByEmail(email)) {
            if (inventoryService.existsByProductName(productName)) {
                customerService.addToCart(customerService.getByEmail(email), inventoryService.getByProductName(productName).getItem());
            } throw new ItemNotFoundException();
        } else throw new CustomerNotFoundException();
    }

    /**
     * Remove from cart.
     *
     * @param productName the product name
     * @param email       the email
     * @throws InvalidCustomerException  the invalid customer exception
     * @throws CustomerNotFoundException the customer not found exception
     * @throws ItemNotFoundException     the item not found exception
     */
    @DeleteMapping(value = "/{email}/cart/remove/{productName}")
    public void removeFromCart(@PathVariable String productName, @PathVariable String email) throws InvalidCustomerException, CustomerNotFoundException, ItemNotFoundException {
        customerService.removeFromCart(customerService.getByEmail(email), productName);
        if (customerService.existsByEmail(email)) {
            if (inventoryService.existsByProductName(productName)) {
                customerService.removeFromCart(customerService.getByEmail(email), productName);
            } throw new ItemNotFoundException();
        } else throw new CustomerNotFoundException();
    }

    /**
     * Empty cart.
     *
     * @param email the email
     * @throws CustomerNotFoundException the customer not found exception
     */
    @PatchMapping(value = "/{email}/cart/empty")
    public void emptyCart(@PathVariable String email) throws CustomerNotFoundException {
        if (customerService.existsByEmail(email)) {
            customerService.emptyCart(customerService.getByEmail(email));
        } else throw new CustomerNotFoundException();
    }

    /**
     * Gets cart.
     *
     * @param email the email
     * @return the cart
     * @throws CustomerNotFoundException the customer not found exception
     */
    @GetMapping(value = "/{email}/cart/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cart getCart(@PathVariable String email) throws CustomerNotFoundException {
        if (customerService.existsByEmail(email)) {
            return customerService.getCart(customerService.getByEmail(email));
        } else throw new CustomerNotFoundException();
    }

    /**
     * Add credit card.
     *
     * @param email      the email
     * @param creditCard the credit card
     * @throws InvalidNameException      the invalid name exception
     * @throws InvalidCustomerException  the invalid customer exception
     * @throws CustomerNotFoundException the customer not found exception
     */
// TODO: Github readme: describe functionalities
    @PostMapping(value = "/{email}/creditCard/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCreditCard(@PathVariable String email, @RequestBody CreditCard creditCard) throws InvalidNameException, InvalidCustomerException, CustomerNotFoundException {
        if (customerService.existsByEmail(email)) {
            // Validate CreditCard
            customerService.addCreditCard(customerService.getByEmail(email), creditCard);
        } else throw new CustomerNotFoundException();
    }

    /**
     * Remove credit card.
     *
     * @param email      the email
     * @param fourDigits the four digits
     * @throws CustomerNotFoundException the customer not found exception
     */
    @DeleteMapping(value = "/{email}/creditCard/remove/{fourDigits}")
    public void removeCreditCard(@PathVariable String email, @PathVariable String fourDigits) throws CustomerNotFoundException {
        if (customerService.existsByEmail(email)) {
            // CreditCardService should validate
            customerService.removeCreditCard(customerService.getByEmail(email), fourDigits);
        } else throw new CustomerNotFoundException();
    }

    /**
     * List credit cards list.
     *
     * @param email the email
     * @return the list
     * @throws CustomerNotFoundException the customer not found exception
     */
    @GetMapping(value = "/{email}/creditCards/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CreditCard> listCreditCards(@PathVariable String email) throws CustomerNotFoundException {
        if (customerService.existsByEmail(email)) {
            return customerService.findAllCreditCards(customerService.getByEmail(email));
        } else throw new CustomerNotFoundException();
    }

    /**
     * Add order.
     *
     * @param email        the email
     * @param orderDetails the order
     * @throws CustomerNotFoundException the customer not found exception
     */
    @PostMapping(value = "/{email}/orderDetails/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addOrder(@PathVariable String email, @RequestBody OrderDetails orderDetails) throws CustomerNotFoundException {
        if (customerService.existsByEmail(email)) {
            // OrderService for validation
            customerService.addOrder(customerService.getByEmail(email), orderDetails);
        } else throw new CustomerNotFoundException();
    }

    /**
     * Update order.
     *
     * @param email  the email
     * @param uuid   the uuid
     * @param status the status
     * @throws CustomerNotFoundException the customer not found exception
     */
    @PutMapping(value = "/{email}/orderDetails/updateStatus/{uuid}/{status}")
    public void updateOrder(@PathVariable String email, @PathVariable UUID uuid, @PathVariable String status) throws CustomerNotFoundException{
        if (customerService.existsByEmail(email)) {
            // Validate
            customerService.updateOrder(customerService.getByEmail(email), uuid, status);
        } else throw new CustomerNotFoundException();
    }

    /**
     * List orderDetails list.
     *
     * @param email the email
     * @return the list
     * @throws CustomerNotFoundException the customer not found exception
     */
    @GetMapping(value = "/{email}/orderDetails/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDetails> listOrders(@PathVariable String email) throws CustomerNotFoundException {
        if (customerService.existsByEmail(email)) {
            return customerService.findAllOrders(customerService.getByEmail(email));
        } else throw new CustomerNotFoundException();
    }
}
