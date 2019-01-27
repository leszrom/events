package com.crud.events.mapper;

import com.crud.events.domain.Member;
import com.crud.events.domain.dto.MemberRequest;
import com.crud.events.domain.dto.MemberResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberMapper {
    public Member mapToMember(MemberRequest memberRequest) {
        return new Member(
                memberRequest.getFirstname(),
                memberRequest.getLastname()
        );
    }

    public MemberResponse mapToMemberResponse(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getFirstname(),
                member.getLastname()
        );
    }

    public List<MemberResponse> mapToMemberResponseList(List<Member> members) {
        return members.stream()
                .map(this::mapToMemberResponse)
                .collect(Collectors.toList());
    }
}
