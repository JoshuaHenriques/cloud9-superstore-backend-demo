package org.jayhenri.cloud9.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Product name not same exception.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProductNameNotSameException extends Exception {
    private List<String> errorMessages = new ArrayList<>();

    /**
     * Instantiates a new Product name not same exception.
     */
    public ProductNameNotSameException() {
    }

    /**
     * Instantiates a new Product name not same exception.
     *
     * @param msg the msg
     */
    public ProductNameNotSameException(String msg) {
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
