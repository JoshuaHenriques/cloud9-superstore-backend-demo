package org.jayhenri.ecommerce.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Invalid item exception.
 */
public class InvalidItemException extends Exception {
    private List<String> errorMessages = new ArrayList<>();

    /**
     * Instantiates a new Invalid item exception.
     */
    public InvalidItemException() {}

    /**
     * Instantiates a new Invalid item exception.
     *
     * @param msg the msg
     */
    public InvalidItemException(String msg) {
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
