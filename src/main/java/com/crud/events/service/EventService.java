package com.crud.events.service;

import com.crud.events.domain.Event;
import com.crud.events.domain.Member;
import com.crud.events.domain.dto.EventRequest;
import com.crud.events.mapper.EventMapper;
import com.crud.events.repository.EventRepository;
import com.crud.events.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;
    private final EventMapper eventMapper;

    @Autowired
    public EventService(EventRepository eventRepository, MemberRepository memberRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.memberRepository = memberRepository;
        this.eventMapper = eventMapper;
    }

    @Transactional
    public Long saveNewEvent(final EventRequest eventRequest) {
        Event event = eventMapper.mapToEvent(eventRequest);
        List<Member> members = memberRepository.retrieveMembersWithRole("VIP");
        members.forEach(member -> event.getMembers().add(member));
        return eventRepository.save(event).getId();
    }
}
