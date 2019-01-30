package com.crud.events.mapper;

import com.crud.events.domain.Event;
import com.crud.events.domain.dto.EventRequest;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    public Event mapToEvent(EventRequest eventRequest) {
        return new Event(eventRequest.getName(), eventRequest.getDescription()
                , eventRequest.getDate());
    }
}
