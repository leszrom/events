package com.crud.events.exception;

public class PermissionNotFoundException extends RuntimeException {
    public PermissionNotFoundException() {
        super("The Permission with the given ROLE doesn't exist!");
    }
}
