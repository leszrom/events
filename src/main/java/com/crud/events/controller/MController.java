package com.crud.events.controller;

import com.crud.events.domain.Member;
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

    @GetMapping("/members/new")
    public String showSignUpForm(Member member) {
        return "add-member";
    }

    @GetMapping("/members")
    public String showAllMembers(Model model) {
        model.addAttribute("members", webPageMemberService.getAllMembers());
        return "index";
    }

    @PostMapping("/members")
    public String addMember(@Valid Member member, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-member";
        }
        webPageMemberService.addMember(member);
        model.addAttribute("member", member);
        model.addAttribute("members", webPageMemberService.getAllMembers());
        return "index";
    }

    @GetMapping("/members/{id}/edit")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Member member = webPageMemberService.getMemberById(id);
        model.addAttribute("member", member);
        return "update-member";
    }

    @PostMapping("/members/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Member member, BindingResult result) {
        if (result.hasErrors()) {
            member.setId(id);
            return "update-member";
        }
        webPageMemberService.addMember(member);
        return "redirect:/members";
    }

    @GetMapping("/members/{id}/delete")
    public String deleteUser(@PathVariable("id") long id) {
        webPageMemberService.removeMemberById(id);
        return "redirect:/members";
    }
}
