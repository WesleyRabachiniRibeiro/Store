package com.store.product.exception;

public class StatusException extends IllegalArgumentException {

    public StatusException(boolean status) {
        super("Product is already " + (status ? "Active" : "Disable"));
    }
}
