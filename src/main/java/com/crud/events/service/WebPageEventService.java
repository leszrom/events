package com.crud.events.service;

import com.crud.events.domain.Event;
import com.crud.events.domain.Member;
import com.crud.events.exception.EventNotFoundException;
import com.crud.events.repository.EventRepository;
import com.crud.events.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.crud.events.domain.Role.VIP;

@Service
public class WebPageEventService {
    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;

    public WebPageEventService(EventRepository eventRepository, MemberRepository memberRepository) {
        this.eventRepository = eventRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Transactional
    public Event getEventById(final Long id) {
        return eventRepository.findById(id)
                .orElseThrow(EventNotFoundException::new);
    }

    @Transactional
    public void saveNewEvent(final Event event) {
        List<Member> members = memberRepository.retrieveMembersWithRole(VIP);
        members.forEach(member -> event.getMembers().add(member));
        eventRepository.save(event);
    }
}
