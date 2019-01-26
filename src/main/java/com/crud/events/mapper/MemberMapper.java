package com.crud.events.mapper;

import com.crud.events.domain.Member;
import com.crud.events.domain.dto.MemberRequest;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public Member mapToMember(MemberRequest memberRequest) {
        return new Member(
                memberRequest.getFirstname(),
                memberRequest.getLastname()
        );
    }
}
