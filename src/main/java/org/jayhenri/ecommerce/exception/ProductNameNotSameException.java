package org.jayhenri.ecommerce.exception;

import java.util.ArrayList;
import java.util.List;

public class ProductNameNotSameException extends Exception {
    private List<String> errorMessages = new ArrayList<>();

    public ProductNameNotSameException() {}

    public ProductNameNotSameException(String msg) {
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
