package org.jayhenri.ecommerce.exception;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * The type Customer already exists exception.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistsException extends Exception {

    private List<String> errorMessages = new ArrayList<>();

    /**
     * Instantiates a new Customer already exists exception.
     */
    public CustomerAlreadyExistsException() {}

    /**
     * Instantiates a new Customer already exists exception.
     *
     * @param msg the msg
     */
    public CustomerAlreadyExistsException(String msg) {
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