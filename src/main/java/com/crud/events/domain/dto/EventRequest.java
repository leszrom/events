package com.crud.events.domain.dto;

public class EventRequest {
    private String name;
    private String description;
    private String date;

    public EventRequest() {
    }

    public EventRequest(String name, String description, String date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }
}
