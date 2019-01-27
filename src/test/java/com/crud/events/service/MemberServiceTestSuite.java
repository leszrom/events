package com.crud.events.service;

import com.crud.events.domain.Member;
import com.crud.events.domain.dto.MemberRequest;
import com.crud.events.domain.dto.MemberResponse;
import com.crud.events.exception.MemberNotFoundException;
import com.crud.events.mapper.MemberMapper;
import com.crud.events.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.CatchException.verifyException;

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
    public void should_return_member_by_given_id() {
        //Given
        Member member = new Member("Firstname", "Lastname");
        MemberResponse memberResponse = new MemberResponse(1L, "Firstname", "Lastname");

        Mockito.when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        Mockito.when(memberMapper.mapToMemberResponse(member)).thenReturn(memberResponse);

        //When
        MemberResponse readMember = memberService.getMemberById(1L);

        //Then
        Assert.assertEquals(1L, readMember.getId());
        Assert.assertEquals("Firstname", readMember.getFirstname());
        Assert.assertEquals("Lastname", readMember.getLastname());
    }

    @Test
    public void should_throw_exception_when_member_with_given_id_does_not_exist() {
        //Given
        String exceptionMessage = "The Member with the given ID doesn't exist!";
        Mockito.when(memberRepository.findById(1L)).thenThrow(new MemberNotFoundException());

        //When
        verifyException(memberService).getMemberById(1L);

        //Then
        Assert.assertEquals(MemberNotFoundException.class, caughtException().getClass());
        Assert.assertEquals(exceptionMessage, caughtException().getMessage());
    }

    @Test
    public void should_return_GetAllMembers() {
        //Given

        //When

        //Then

    }


}