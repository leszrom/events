package com.crud.events.controller;

import com.crud.events.domain.Member;
import com.crud.events.domain.Role;
import com.crud.events.service.WebPageMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MController {
    private final WebPageMemberService webPageMemberService;

    @Autowired
    public MController(WebPageMemberService webPageMemberService) {
        this.webPageMemberService = webPageMemberService;
    }

    @GetMapping("/members")
    public String showAllMembers(Model model) {
        model.addAttribute("members", webPageMemberService.getAllMembers());
        model.addAttribute("vips", webPageMemberService.getMembersByRole(Role.VIP));
        model.addAttribute("nonVips", webPageMemberService.getMembersByRole(Role.NON_VIP));
        model.addAttribute("admins", webPageMemberService.getMembersByRole(Role.ADMIN));
        return "index";
    }

    @GetMapping("/members/new")
    public String showCreateMemberForm(Member member, Model model) {
        model.addAttribute("member", member);
        model.addAttribute("allPermissions", webPageMemberService.getAllPermissions());
        return "add-member";
    }

    @PostMapping("/members")
    public String addMember(@Valid Member member, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return showCreateMemberForm(member, model);
        }
        webPageMemberService.addMember(member);
        return "redirect:/members";
    }

    @GetMapping("/members/{id}/edit")
    public String showUpdateMemberForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("member", webPageMemberService.getMemberById(id));
        model.addAttribute("allPermissions", webPageMemberService.getAllPermissions());
        return "update-member";
    }

    @PostMapping("/members/{id}")
    public String updateMember(@PathVariable("id") long id, @Valid Member member, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("member", member);
            model.addAttribute("allPermissions", webPageMemberService.getAllPermissions());
            return "update-member";
        }
        webPageMemberService.addMember(member);
        return "redirect:/members";
    }

    @GetMapping("/members/{id}/delete")
    public String deleteMember(@PathVariable("id") long id) {
        webPageMemberService.removeMemberById(id);
        return "redirect:/members";
    }
}
