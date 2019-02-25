package com.crud.events.service;

import com.crud.events.domain.Event;
import com.crud.events.repository.EventRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class WebPageEventService {
    private final EventRepository eventRepository;

    public WebPageEventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
