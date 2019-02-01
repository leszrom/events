package com.crud.events.mapper;

import com.crud.events.domain.Member;
import com.crud.events.domain.dto.MemberRequest;
import com.crud.events.domain.dto.MemberResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MemberMapperTestSuite {
    private MemberMapper memberMapper = new MemberMapper();

    @Test
    public void should_return_member_mapped_from_member_request() {
        //Given
        MemberRequest memberRequest = new MemberRequest("Firstname", "Lastname");

        //When
        Member member = memberMapper.mapToMember(memberRequest);

        //Then
        Assert.assertEquals("Firstname", member.getFirstname());
        Assert.assertEquals("Lastname", member.getLastname());
    }

    @Test
    public void should_return_member_response_mapped_from_member() {
        //Given
        Member member = new Member(1L, "Firstname", "Lastname");

        //When
        MemberResponse memberResponse = memberMapper.mapToMemberResponse(member);

        //Then
        Assert.assertEquals(1L, memberResponse.getId());
        Assert.assertEquals("Firstname", memberResponse.getFirstname());
        Assert.assertEquals("Lastname", memberResponse.getLastname());
    }

    @Test
    public void should_return_member_response_list_mapped_from_members_list() {
        //Given
        List<Member> members = new ArrayList<>();
        members.add(new Member(1L, "Firstname", "Lastname"));

        //When
        List<MemberResponse> memberResponseList = memberMapper.mapToMemberResponseList(members);

        //Then
        memberResponseList.forEach(member -> {
            Assert.assertEquals(1L, member.getId());
            Assert.assertEquals("Firstname", member.getFirstname());
            Assert.assertEquals("Lastname", member.getLastname());
        });
    }
}
