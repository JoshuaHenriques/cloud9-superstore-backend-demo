package org.jayhenri.cloud9.exception.invalid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Invalid item exception.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidLoginException extends Exception {
    private List<String> errorMessages = new ArrayList<>();

    /**
     * Instantiates a new Invalid item exception.
     */
    public InvalidLoginException() {
    }

    /**
     * Instantiates a new Invalid item exception.
     *
     * @param msg the msg
     */
    public InvalidLoginException(String msg) {
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
