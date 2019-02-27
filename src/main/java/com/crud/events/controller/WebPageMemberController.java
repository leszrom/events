package com.crud.events.controller;

import com.crud.events.service.WebPageMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import static com.crud.events.domain.Role.*;

@Controller
@RequestMapping("v1/front/")
public class WebPageMemberController {
    private WebPageMemberService webPageMemberService;

    public WebPageMemberController(WebPageMemberService webPageMemberService) {
        this.webPageMemberService = webPageMemberService;
    }

    @RequestMapping(value = "members", method = RequestMethod.GET)
    public ModelAndView getMembersView() {
        ModelAndView modelAndView = new ModelAndView("members");
        modelAndView.addObject("members", webPageMemberService.getAllMembers());
        modelAndView.addObject("vip_members", webPageMemberService.getMembersByRole(VIP));
        modelAndView.addObject("non_vip_members", webPageMemberService.getMembersByRole(NON_VIP));
        modelAndView.addObject("admin_members", webPageMemberService.getMembersByRole(ADMIN));
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "members/{id}")
    public ModelAndView getMember(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("member");
        modelAndView.addObject("member", webPageMemberService.getMemberById(id));
        return modelAndView;
    }
}
