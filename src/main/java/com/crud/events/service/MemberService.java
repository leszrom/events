package com.crud.events.service;

import com.crud.events.domain.Member;
import com.crud.events.domain.dto.MemberRequest;
import com.crud.events.domain.dto.MemberResponse;
import com.crud.events.exception.MemberNotFoundException;
import com.crud.events.mapper.MemberMapper;
import com.crud.events.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Autowired
    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    @Transactional
    public Long saveMember(final MemberRequest memberRequest) {
        Member member = memberMapper.mapToMember(memberRequest);
        return memberRepository.save(member).getId();
    }

    @Transactional
    public MemberResponse getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new);
        return memberMapper.mapToMemberResponse(member);
    }
}
