package com.crud.events.controller.rest;

import com.crud.events.domain.dto.EventRequest;
import com.crud.events.service.EventService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("v1")
public class EventControllerRest {
    private final EventService eventService;

    public EventControllerRest(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "events", consumes = APPLICATION_JSON_VALUE)
    public Long createEvent(@RequestBody EventRequest eventRequest) {
        return eventService.saveNewEvent(eventRequest);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "events/{eventId}/members")
    public void addEventMember(Long memberId, @PathVariable Long eventId) {
        eventService.addMemberToEvent(memberId, eventId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "events/{eventId}/members")
    public void removeEventMember(Long memberId, @PathVariable Long eventId) {
        eventService.removeMemberFromEvent(memberId, eventId);
    }
}
