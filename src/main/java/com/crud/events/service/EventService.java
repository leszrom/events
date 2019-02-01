package com.crud.events.service;

import com.crud.events.domain.Event;
import com.crud.events.domain.Member;
import com.crud.events.domain.dto.EventRequest;
import com.crud.events.exception.EventNotFoundException;
import com.crud.events.exception.MemberNotFoundException;
import com.crud.events.mapper.EventMapper;
import com.crud.events.repository.EventRepository;
import com.crud.events.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.crud.events.domain.Role.VIP;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;
    private final EventMapper eventMapper;

    public EventService(EventRepository eventRepository, MemberRepository memberRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.memberRepository = memberRepository;
        this.eventMapper = eventMapper;
    }

    @Transactional
    public Long saveNewEvent(final EventRequest eventRequest) {
        Event event = eventMapper.mapToEvent(eventRequest);
        List<Member> members = memberRepository.retrieveMembersWithRole(VIP);
        members.forEach(member -> event.getMembers().add(member));
        return eventRepository.save(event).getId();
    }

    @Transactional
    public void addMemberToEvent(final Long memberId, final Long eventId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
        Event event = eventRepository.findById(eventId)
                .orElseThrow(EventNotFoundException::new);
        event.getMembers().add(member);
    }

    @Transactional
    public void removeMemberFromEvent(final Long memberId, final Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(EventNotFoundException::new);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
        event.getMembers().remove(member);
    }
}
