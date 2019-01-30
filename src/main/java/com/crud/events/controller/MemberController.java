package com.crud.events.controller;

import com.crud.events.domain.dto.MemberRequest;
import com.crud.events.domain.dto.MemberResponse;
import com.crud.events.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("v1/members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public Long createMember(@RequestBody MemberRequest memberRequest) {
        return memberService.saveMember(memberRequest);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public MemberResponse getMember(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<MemberResponse> getMembers() {
        return memberService.getAllMembers();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMemberById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE, value = "{id}")
    public MemberResponse updateMember(@RequestBody MemberRequest memberRequest, @PathVariable Long id) {
        return memberService.updateMemberDetails(id, memberRequest);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}/permissions")
    public void addPermission(@PathVariable Long id, String role) {
        memberService.addPermissionByMemberId(id, role);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}/permissions/{role}")
    public void revokePermission(@PathVariable Long id, @PathVariable String role) {
        memberService.revokePermissionByMemberId(id, role);
    }
}
