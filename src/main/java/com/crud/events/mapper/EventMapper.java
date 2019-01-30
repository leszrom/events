package com.crud.events.mapper;

import com.crud.events.domain.Event;
import com.crud.events.domain.dto.EventRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class EventMapper {
    public Event mapToEvent(EventRequest eventRequest) {
        return new Event(eventRequest.getName(), eventRequest.getDescription()
                , LocalDateTime.parse(eventRequest.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}
