package com.crud.events.service;

import com.crud.events.domain.Event;
import com.crud.events.domain.Member;
import com.crud.events.domain.dto.EventRequest;
import com.crud.events.mapper.EventMapper;
import com.crud.events.repository.EventRepository;
import com.crud.events.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTestSuite {

    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private EventMapper eventMapper;

    @Test
    public void should_create_new_event_with_default_all_vip_members() {
        //Given
        EventRequest eventRequest = new EventRequest("Name", "Description");
        Event event = new Event("Name", "Description");
        Event savedEvent = new Event(5L, "Name", "Description");

        List<Member> vipMembers = new ArrayList<>();
        vipMembers.add(new Member(1L, "Firstname", "Lastname"));

        Mockito.when(eventMapper.mapToEvent(eventRequest)).thenReturn(event);
        Mockito.when(memberRepository.retrieveMembersWithRole("VIP")).thenReturn(vipMembers);
        Mockito.when(eventRepository.save(event)).thenReturn(savedEvent);

        //When
        Long id = eventService.saveNewEvent(eventRequest);

        //Then
        Mockito.verify(eventRepository, Mockito.times(1)).save(event);
        Assert.assertEquals(5L, (long) id);
        event.getMembers().forEach(member -> {
                    Assert.assertEquals(1L, (long) member.getId());
                    Assert.assertEquals("Firstname", member.getFirstname());
                    Assert.assertEquals("Lastname", member.getLastname());
                }
        );
    }
}