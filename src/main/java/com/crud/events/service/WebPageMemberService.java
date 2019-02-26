package com.crud.events.service;

import com.crud.events.domain.Member;
import com.crud.events.exception.MemberNotFoundException;
import com.crud.events.repository.MemberRepository;
import com.crud.events.repository.PermissionRepository;
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
}
