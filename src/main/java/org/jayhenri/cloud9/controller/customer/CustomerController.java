package org.jayhenri.cloud9.controller.customer;

import org.jayhenri.cloud9.exception.CustomerNotFoundException;
import org.jayhenri.cloud9.exception.InvalidCustomerException;
import org.jayhenri.cloud9.exception.ItemNotFoundException;
import org.jayhenri.cloud9.model.customer.Customer;
import org.jayhenri.cloud9.service.customer.CustomerService;
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
 * The type Customer controller.
 */
@RestController // Indicates that the data returned by each method will be written straight into
// the response body instead of rendering a template
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
     * Register response entity.
     *
     * @param customer the customer
     * @return the response entity
     * @throws CustomerAlreadyExistsException the customer already exists exception
     * @throws InvalidPostalCodeException     the invalid postal code exception
     * @throws InvalidCustomerException       the invalid customer exception
     */
    @PostMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody Customer customer)
            throws CustomerAlreadyExistsException, InvalidPostalCodeException, InvalidCustomerException {

        if (ObjectUtils.isEmpty(customer))
            throw new InvalidCustomerException();

        else if (customerService.existsByPhoneNumber(customer.getPhoneNumber())
                || customerService.existsByEmail(customer.getEmail()))
            throw new CustomerAlreadyExistsException();

        else if (!addressService.isValidPostalCode(customer.getAddress().getPostalCode()))
            throw new InvalidPostalCodeException();

        customerService.add(customer);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("CustomerRegistrationController", "register");
        return new ResponseEntity<>("Successfully Created Customer", responseHeaders, HttpStatus.CREATED);
    }

    /**
     * Update customer.
     *
     * @param customer the customer
     * @return the response entity
     * @throws InvalidCustomerException  the invalid customer exception
     * @throws CustomerNotFoundException the customer not found exception
     */
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer)
            throws InvalidCustomerException, CustomerNotFoundException {
        if (!ObjectUtils.isEmpty(customer)) {
            if (customerService.existsByEmail(customer.getEmail())) {
                customerService.update(customer);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("CustomerController", "updateCustomer");
                return new ResponseEntity<>("Successfully Updated Customer", responseHeaders, HttpStatus.OK);
            } else
                throw new CustomerNotFoundException();
        } else
            throw new InvalidCustomerException();
    }

    /**
     * Delete customer.
     *
     * @param email the email
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     */
    @DeleteMapping(value = "/delete/{email}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String email)
            throws CustomerNotFoundException {
        if (customerService.existsByEmail(email)) {
            Customer _customer = customerService.getByEmail(email);
            customerService.delete(_customer);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("CustomerController", "deleteCustomer");
            return new ResponseEntity<>("Successfully Deleted Customer", responseHeaders, HttpStatus.OK);
        } else
            throw new CustomerNotFoundException();
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

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("CustomerController", "listCustomers");
        return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
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
    public ResponseEntity<Customer> getByEmail(@PathVariable String email)
            throws InvalidNameException, CustomerNotFoundException {
        if (!ObjectUtils.isEmpty(email)) {
            if (customerService.existsByEmail(email)) {
                Customer _customer = customerService.getByEmail(email);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("CustomerController", "getByEmail");
                return new ResponseEntity<>(_customer, responseHeaders, HttpStatus.OK);
            } else
                throw new CustomerNotFoundException();
        } else
            throw new InvalidNameException();
    }

    /**
     * Add to cart.
     *
     * @param productName the product name
     * @param email       the email
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     * @throws ItemNotFoundException     the item not found exception
     */
    @PostMapping(value = "/{email}/cart/add/{productName}")
    public ResponseEntity<String> addToCart(@PathVariable String productName, @PathVariable String email)
            throws CustomerNotFoundException, ItemNotFoundException {
        if (customerService.existsByEmail(email)) {
            if (inventoryService.existsByProductName(productName)) {
                Customer customer = customerService.getByEmail(email);
                Inventory inventory = inventoryService.getByProductName(productName);
                Item item = inventory.getItem();

                customerService.addToCart(customer, item);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("CustomerController", "addToCart");
                return new ResponseEntity<>("Successfully Added to Cart", responseHeaders, HttpStatus.CREATED);
            } else
                throw new ItemNotFoundException();
        } else
            throw new CustomerNotFoundException();
    }

    /**
     * Remove from cart.
     *
     * @param productName the product name
     * @param email       the email
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     * @throws ItemNotFoundException     the item not found exception
     */
    @DeleteMapping(value = "/{email}/cart/remove/{productName}")
    public ResponseEntity<String> removeFromCart(@PathVariable String productName, @PathVariable String email)
            throws CustomerNotFoundException, ItemNotFoundException {
        if (customerService.existsByEmail(email)) {
            if (inventoryService.existsByProductName(productName)) {
                customerService.removeFromCart(customerService.getByEmail(email), productName);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("CustomerController", "removeFromCart");
                return new ResponseEntity<>("Successfully Removed from Cart", responseHeaders, HttpStatus.OK);
            } else
                throw new ItemNotFoundException();
        } else
            throw new CustomerNotFoundException();
    }

    /**
     * Empty cart.
     *
     * @param email the email
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     */
    @PatchMapping(value = "/{email}/cart/empty")
    public ResponseEntity<String> emptyCart(@PathVariable String email) throws CustomerNotFoundException {
        if (customerService.existsByEmail(email)) {
            customerService.emptyCart(customerService.getByEmail(email));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("CustomerController", "emptyCart");
            return new ResponseEntity<>("Successfully Emptied Cart", responseHeaders, HttpStatus.OK);
        } else
            throw new CustomerNotFoundException();
    }

    /**
     * Gets cart.
     *
     * @param email the email
     * @return the cart
     * @throws CustomerNotFoundException the customer not found exception
     */
    @GetMapping(value = "/{email}/cart/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cart> getCart(@PathVariable String email) throws CustomerNotFoundException {
        if (customerService.existsByEmail(email)) {
            Cart _cart = customerService.getCart(customerService.getByEmail(email));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("CustomerController", "getCart");
            return new ResponseEntity<>(_cart, responseHeaders, HttpStatus.OK);
        } else
            throw new CustomerNotFoundException();
    }

    /**
     * Add credit card.
     *
     * @param email      the email
     * @param creditCard the credit card
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     */
    @PostMapping(value = "/{email}/creditCard/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCreditCard(@PathVariable String email, @RequestBody CreditCard creditCard)
            throws CustomerNotFoundException {
        if (customerService.existsByEmail(email)) {
            // Validate CreditCard
            customerService.addCreditCard(customerService.getByEmail(email), creditCard);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("CustomerController", "addCreditCard");
            return new ResponseEntity<>("Successfully Added Credit Card", responseHeaders, HttpStatus.CREATED);
        } else
            throw new CustomerNotFoundException();
    }

    /**
     * Remove credit card.
     *
     * @param email      the email
     * @param fourDigits the four digits
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     */
    @DeleteMapping(value = "/{email}/creditCard/remove/{fourDigits}")
    public ResponseEntity<String> removeCreditCard(@PathVariable String email, @PathVariable String fourDigits)
            throws CustomerNotFoundException {
        if (customerService.existsByEmail(email)) {
            // CreditCardService should validate
            // todo: compare customer's creditcard's last four digits with fourdigits
            customerService.removeCreditCard(customerService.getByEmail(email), fourDigits);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("CustomerController", "removeCreditCard");
            return new ResponseEntity<>("Successfully Removed Credit Card", responseHeaders, HttpStatus.OK);
        } else
            throw new CustomerNotFoundException();
    }

    /**
     * List credit cards list.
     *
     * @param email the email
     * @return the list
     * @throws CustomerNotFoundException the customer not found exception
     */
    @GetMapping(value = "/{email}/creditCards/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CreditCard>> listCreditCards(@PathVariable String email)
            throws CustomerNotFoundException {
        if (customerService.existsByEmail(email)) {
            List<CreditCard> list = customerService.findAllCreditCards(customerService.getByEmail(email));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("CustomerController", "listCreditCards");
            return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
        } else
            throw new CustomerNotFoundException();
    }

    /**
     * Add order.
     *
     * @param email        the email
     * @param orderDetails the order
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     */
    @PostMapping(value = "/{email}/orderDetails/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addOrder(@PathVariable String email, @RequestBody OrderDetails orderDetails)
            throws CustomerNotFoundException {
        if (customerService.existsByEmail(email)) {
            // OrderService for validation
            customerService.addOrder(customerService.getByEmail(email), orderDetails);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("CustomerController", "addOrder");
            return new ResponseEntity<>("Successfully Added Order", responseHeaders, HttpStatus.CREATED);
        } else
            throw new CustomerNotFoundException();
    }

    /**
     * Update order.
     *
     * @param email  the email
     * @param uuid   the uuid
     * @param status the status
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     */
    @PutMapping(value = "/{email}/orderDetails/updateStatus/{uuid}/{status}")
    public ResponseEntity<String> updateOrder(@PathVariable String email, @PathVariable UUID uuid,
                                              @PathVariable String status) throws CustomerNotFoundException {
        if (customerService.existsByEmail(email)) {
            // Validate
            customerService.updateOrder(customerService.getByEmail(email), uuid, status);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("CustomerController", "updateOrder");
            return new ResponseEntity<>("Successfully Updated Order", responseHeaders, HttpStatus.OK);
        } else
            throw new CustomerNotFoundException();
    }

    /**
     * List orderDetails list.
     *
     * @param email the email
     * @return the list
     * @throws CustomerNotFoundException the customer not found exception
     */
    @GetMapping(value = "/{email}/orderDetails/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDetails>> listOrders(@PathVariable String email) throws CustomerNotFoundException {
        if (customerService.existsByEmail(email)) {
            List<OrderDetails> list = customerService.findAllOrders(customerService.getByEmail(email));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("CustomerController", "listOrders");
            return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
        } else
            throw new CustomerNotFoundException();
    }
}
