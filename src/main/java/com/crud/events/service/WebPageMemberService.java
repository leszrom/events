package com.crud.events.service;

import com.crud.events.domain.Member;
import com.crud.events.repository.MemberRepository;
import com.crud.events.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class WebPageMemberService {
    private MemberRepository memberRepository;
    private PermissionRepository permissionRepository;

    public WebPageMemberService(MemberRepository memberRepository, PermissionRepository permissionRepository) {
        this.memberRepository = memberRepository;
        this.permissionRepository = permissionRepository;
    }

    @Transactional
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
