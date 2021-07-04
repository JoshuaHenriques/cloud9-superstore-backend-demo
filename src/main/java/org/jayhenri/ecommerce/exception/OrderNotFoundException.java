package org.jayhenri.ecommerce.exception;

import java.util.ArrayList;
import java.util.List;

public class OrderNotFoundException extends Exception {
    private List<String> errorMessages = new ArrayList<>();

    public OrderNotFoundException() {}

    public OrderNotFoundException(String msg) {
        super(msg);
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public void addErrorMessage(String msg) {
        this.errorMessages.add(msg);
    }
}
