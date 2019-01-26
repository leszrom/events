package com.crud.events.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException() {
        super("The Member with the given id does not exist");
    }
}
