package com.crud.events.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException() {
        super("The Member with the given ID doesn't exist!");
    }
}
