package com.crud.events.controller.rest;

import com.crud.events.domain.Role;
import com.crud.events.domain.dto.MemberRequest;
import com.crud.events.domain.dto.MemberResponse;
import com.crud.events.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("v1")
public class MemberControllerRest {
    private final MemberService memberService;

    public MemberControllerRest(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "members", consumes = APPLICATION_JSON_VALUE)
    public Long createMember(@RequestBody MemberRequest memberRequest) {
        return memberService.saveMember(memberRequest);
    }

    @RequestMapping(method = RequestMethod.GET, value = "members/{id}")
    public MemberResponse getMember(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "members")
    public List<MemberResponse> getMembers() {
        return memberService.getAllMembers();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "members/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMemberById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE, value = "members/{id}")
    public MemberResponse updateMember(@RequestBody MemberRequest memberRequest, @PathVariable Long id) {
        return memberService.updateMemberDetails(id, memberRequest);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "members/{id}/permissions")
    public void addPermission(@PathVariable Long id, Role role) {
        memberService.addPermissionByMemberId(id, role);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "members/{id}/permissions")
    public void revokePermission(@PathVariable Long id, Role role) {
        memberService.revokePermissionByMemberId(id, role);
    }
}
