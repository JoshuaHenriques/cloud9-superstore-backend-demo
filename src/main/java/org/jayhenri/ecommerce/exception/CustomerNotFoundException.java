package org.jayhenri.ecommerce.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Customer not found exception.
 */
public class CustomerNotFoundException extends Exception {
    private List<String> errorMessages = new ArrayList<>();

    /**
     * Instantiates a new Customer not found exception.
     */
    public CustomerNotFoundException() {}

    /**
     * Instantiates a new Customer not found exception.
     *
     * @param msg the msg
     */
    public CustomerNotFoundException(String msg) {
        super(msg);
    }

    /**
     * Gets error messages.
     *
     * @return the error messages
     */
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    /**
     * Sets error messages.
     *
     * @param errorMessages the error messages
     */
    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    /**
     * Add error message.
     *
     * @param msg the msg
     */
    public void addErrorMessage(String msg) {
        this.errorMessages.add(msg);
    }
}
