package org.jayhenri.ecommerce.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Email not same exception.
 */
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailNotSameException extends Exception {
    private List<String> errorMessages = new ArrayList<>();

    /**
     * Instantiates a new Email not same exception.
     */
    public EmailNotSameException() {}

    /**
     * Instantiates a new Email not same exception.
     *
     * @param msg the msg
     */
    public EmailNotSameException(String msg) {
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
