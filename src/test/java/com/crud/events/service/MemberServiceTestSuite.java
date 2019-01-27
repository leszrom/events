package com.crud.events.service;

import com.crud.events.domain.Member;
import com.crud.events.domain.dto.MemberRequest;
import com.crud.events.mapper.MemberMapper;
import com.crud.events.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceTestSuite {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MemberMapper memberMapper;

    @Test
    public void should_return_id_of_the_saved_member() {
        //Given
        MemberRequest memberRequest = new MemberRequest("Firstname", "Lastname");
        Member member = new Member("Firstname", "Lastname");
        Member savedMember = new Member(1L, "Firstname", "Lastname");

        Mockito.when(memberMapper.mapToMember(memberRequest)).thenReturn(member);
        Mockito.when(memberRepository.save(member)).thenReturn(savedMember);

        //When
        Long id = memberService.saveMember(memberRequest);

        //Then
        Assert.assertEquals(1L, (long) id);
    }

    @Test
    public void should_return_GetMemberById() {
        //Given

        //When

        //Then

    }

    @Test
    public void should_return_GetAllMembers() {
        //Given

        //When

        //Then

    }


}