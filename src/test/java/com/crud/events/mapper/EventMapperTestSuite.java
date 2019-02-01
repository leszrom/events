package com.crud.events.mapper;

import com.crud.events.domain.Event;
import com.crud.events.domain.dto.EventRequest;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class EventMapperTestSuite {
    private EventMapper eventMapper = new EventMapper();

    @Test
    public void should_return_event_mapped_from_event_request() {
        //Given
        EventRequest eventRequest = new EventRequest("Name", "Description"
                , LocalDateTime.parse("2018-01-23T14:30"));
        //When
        Event event = eventMapper.mapToEvent(eventRequest);

        //Then
        Assert.assertEquals("Name", event.getName());
        Assert.assertEquals("Description", event.getDescription());
        Assert.assertEquals(LocalDateTime.parse("2018-01-23T14:30"), event.getDate());
    }
}