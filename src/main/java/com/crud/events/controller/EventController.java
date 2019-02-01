package com.crud.events.controller;

import com.crud.events.domain.dto.EventRequest;
import com.crud.events.service.EventService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public Long createEvent(@RequestBody EventRequest eventRequest) {
        return eventService.saveNewEvent(eventRequest);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{eventId}/members")
    public void addEventMember(Long memberId, @PathVariable Long eventId) {
        eventService.addMemberToEvent(memberId, eventId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{eventId}/members")
    public void removeEventMember(Long memberId, @PathVariable Long eventId) {
        eventService.removeMemberFromEvent(memberId, eventId);
    }
}
