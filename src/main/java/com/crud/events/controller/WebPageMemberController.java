package com.crud.events.controller;

import com.crud.events.service.WebPageMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("v1/front/")
public class WebPageMemberController {
    private WebPageMemberService webPageMemberService;

    public WebPageMemberController(WebPageMemberService webPageMemberService) {
        this.webPageMemberService = webPageMemberService;
    }

    @RequestMapping(value = "members", method = RequestMethod.GET)
    public ModelAndView getMembersView() {
        ModelAndView modelAndView = new ModelAndView("allMembers");
        modelAndView.addObject("members", webPageMemberService.getAllMembers());
        return modelAndView;
    }
}
