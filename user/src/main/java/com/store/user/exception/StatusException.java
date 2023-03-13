package com.store.user.exception;

public class StatusException extends IllegalArgumentException {

    public StatusException(boolean status) {
        super("User is already " + (status ? "Active" : "Disable"));
    }
}
