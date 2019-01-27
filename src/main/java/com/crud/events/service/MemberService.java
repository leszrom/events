package com.crud.events.service;

import com.crud.events.domain.Member;
import com.crud.events.domain.Permission;
import com.crud.events.domain.dto.MemberRequest;
import com.crud.events.domain.dto.MemberResponse;
import com.crud.events.exception.MemberNotFoundException;
import com.crud.events.exception.PermissionNotFoundException;
import com.crud.events.mapper.MemberMapper;
import com.crud.events.repository.MemberRepository;
import com.crud.events.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PermissionRepository permissionRepository;
    private final MemberMapper memberMapper;

    @Autowired
    public MemberService(MemberRepository memberRepository, PermissionRepository permissionRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.permissionRepository = permissionRepository;
        this.memberMapper = memberMapper;
    }

    @Transactional
    public Long saveMember(final MemberRequest memberRequest) {
        Member member = memberMapper.mapToMember(memberRequest);
        return memberRepository.save(member).getId();
    }

    @Transactional
    public MemberResponse getMemberById(final Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new);
        return memberMapper.mapToMemberResponse(member);
    }

    @Transactional
    public List<MemberResponse> getAllMembers() {
        return memberMapper.mapToMemberResponseList(memberRepository.findAll());
    }

    @Transactional
    public void deleteMemberById(final Long id) {
        memberRepository.deleteById(id);
    }

    @Transactional
    public void addPermissionByMemberId(final Long id, final String role) {
        Member member = memberRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new);
        Permission permission = permissionRepository.findByRole(role)
                .orElseThrow(PermissionNotFoundException::new);
        member.getPermissions().add(permission);
    }
}
