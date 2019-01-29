package com.crud.events.exception;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException() {
        super("The Event with the given ID doesn't exist!");
    }
}
