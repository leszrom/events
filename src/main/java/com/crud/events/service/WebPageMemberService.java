package com.crud.events.service;

import com.crud.events.domain.Member;
import com.crud.events.domain.Role;
import com.crud.events.exception.MemberNotFoundException;
import com.crud.events.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class WebPageMemberService {
    private MemberRepository memberRepository;

    public WebPageMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Transactional
    public Member getMemberById(final Long id) {
        return memberRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Transactional
    public List<Member> getMembersByRole(Role role) {
        return memberRepository.retrieveMembersWithRole(role);
    }

    @Transactional
    public void addMember(Member member) {
        memberRepository.save(member);
    }

    @Transactional
    public void removeMemberById(final Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new);
        member.getEvents().forEach(event -> event.getMembers().remove(member));
        memberRepository.deleteById(id);
    }
}
