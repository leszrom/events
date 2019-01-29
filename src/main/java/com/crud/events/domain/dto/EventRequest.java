package com.crud.events.domain.dto;

public class EventRequest {
    private String name;
    private String description;

    public EventRequest() {
    }

    public EventRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
