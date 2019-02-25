package com.crud.events.service;

import com.crud.events.domain.Member;
import com.crud.events.repository.EventRepository;
import com.crud.events.repository.MemberRepository;
import com.crud.events.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class WebPageService {
    private EventRepository eventRepository;
    private MemberRepository memberRepository;
    private PermissionRepository permissionRepository;

    public WebPageService(EventRepository eventRepository, MemberRepository memberRepository, PermissionRepository permissionRepository) {
        this.eventRepository = eventRepository;
        this.memberRepository = memberRepository;
        this.permissionRepository = permissionRepository;
    }

    @Transactional
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
