package com.crud.events.controller;

import com.crud.events.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("v1/front/")
public class WebPageController {
    private final MemberService memberService;

    public WebPageController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(value = "members", method = RequestMethod.GET)
    public ModelAndView getMembersView() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("members", memberService.getAllMembers());
        return modelAndView;
    }
}
