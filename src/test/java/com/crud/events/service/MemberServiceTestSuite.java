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
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.CatchException.verifyException;
import static java.util.Collections.emptyList;
import static org.mockito.Matchers.anyList;

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceTestSuite {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PermissionRepository permissionRepository;

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
    public void should_return_list_of_all_members() {
        //Given
        List<MemberResponse> memberResponseList = new ArrayList<>();
        memberResponseList.add(new MemberResponse(1L, "Firstname", "Lastname"));

        Mockito.when(memberRepository.findAll()).thenReturn(new ArrayList<>());
        Mockito.when(memberMapper.mapToMemberResponseList(anyList())).thenReturn(memberResponseList);

        //When
        List<MemberResponse> readMembers = memberService.getAllMembers();

        //Then
        readMembers.forEach(member -> {
                    Assert.assertEquals(1L, member.getId());
                    Assert.assertEquals("Firstname", member.getFirstname());
                    Assert.assertEquals("Lastname", member.getLastname());
                }
        );
    }

    @Test
    public void should_return_empty_list_if_there_is_no_member_in_database() {
        //Given
        Mockito.when(memberRepository.findAll()).thenReturn(emptyList());
        Mockito.when(memberMapper.mapToMemberResponseList(emptyList())).thenReturn(new ArrayList<>());

        //When
        List<MemberResponse> readMembers = memberService.getAllMembers();

        //Then
        Assert.assertEquals(0, readMembers.size());
    }

    @Test
    public void should_delete_task_by_given_id() {
        //Given
        //When
        memberService.deleteMemberById(1L);

        //Then
        Mockito.verify(memberRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void should_update_member_details_if_member_with_given_id_exist() {
        //Given
        Member member = new Member(1L, "Firstname", "Lastname");
        MemberRequest memberRequest = new MemberRequest("newFirstname", "newLastname");

        Mockito.when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        //When
        memberService.updateMemberDetails(1L, memberRequest);

        //Then
        Mockito.verify(memberMapper, Mockito.times(1)).mapToMemberResponse(member);
        Assert.assertEquals(1L, (long) member.getId());
        Assert.assertEquals("newFirstname", member.getFirstname());
        Assert.assertEquals("newLastname", member.getLastname());
    }


    @Test
    public void should_throw_exception_when_try_to_update_member_which_does_not_exist() {
        //Given
        String exceptionMessage = "The Member with the given ID doesn't exist!";
        MemberRequest memberRequest = new MemberRequest("newFirstname", "newLastname");

        Mockito.when(memberRepository.findById(1L)).thenThrow(new MemberNotFoundException());

        //When
        verifyException(memberService).updateMemberDetails(1L, memberRequest);

        //Then
        Assert.assertEquals(MemberNotFoundException.class, caughtException().getClass());
        Assert.assertEquals(exceptionMessage, caughtException().getMessage());

    }

    @Test
    public void should_add_permission_with_given_role_for_member_by_given_id() {
        //Given
        Member member = new Member(1L, "Firstname", "Lastname");
        Permission permission = new Permission("VIP");

        Mockito.when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        Mockito.when(permissionRepository.findByRole("VIP")).thenReturn(Optional.of(permission));

        //When
        memberService.addPermissionByMemberId(1L, "VIP");

        //Then
        Assert.assertTrue(member.getPermissions().contains(permission));
    }

    @Test
    public void should_throw_exception_when_permission_with_given_role_does_not_exist() {
        //Given
        String exceptionMessage = "The Permission with the given ROLE doesn't exist!";
        Member member = new Member(1L, "Firstname", "Lastname");

        Mockito.when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        Mockito.when(permissionRepository.findByRole("role")).thenThrow(new PermissionNotFoundException());

        //When
        verifyException(memberService).addPermissionByMemberId(1L, "role");

        //Then
        Assert.assertEquals(PermissionNotFoundException.class, caughtException().getClass());
        Assert.assertEquals(exceptionMessage, caughtException().getMessage());
    }

    @Test
    public void should_throw_exception_when_try_to_add_permission_for_member_which_does_not_exist() {
        //Given
        String exceptionMessage = "The Member with the given ID doesn't exist!";
        Permission permission = new Permission("VIP");

        Mockito.when(memberRepository.findById(1L)).thenThrow(new MemberNotFoundException());
        Mockito.when(permissionRepository.findByRole("VIP")).thenReturn(Optional.of(permission));

        //When
        verifyException(memberService).addPermissionByMemberId(1L, "role");

        //Then
        Assert.assertEquals(MemberNotFoundException.class, caughtException().getClass());
        Assert.assertEquals(exceptionMessage, caughtException().getMessage());
    }

    @Test
    public void should_revoke_permission_with_given_role_for_member_by_given_id() {
        //Given
        Member member = new Member(1L, "Firstname", "Lastname");
        Permission permission = new Permission("VIP");
        member.getPermissions().add(permission);

        Mockito.when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        Mockito.when(permissionRepository.findByRole("VIP")).thenReturn(Optional.of(permission));

        //When
        memberService.revokePermissionByMemberId(1L, "VIP");

        //Then
        Assert.assertEquals(0, member.getPermissions().size());
    }
}
