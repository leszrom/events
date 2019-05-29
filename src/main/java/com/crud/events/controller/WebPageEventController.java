package com.crud.events.controller;

import com.crud.events.domain.Event;
import com.crud.events.domain.Member;
import com.crud.events.service.WebPageEventService;
import com.crud.events.service.WebPageMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

import static com.crud.events.domain.Role.NON_VIP;
import static com.crud.events.domain.Role.VIP;

@Controller
@RequestMapping("v1/front/")
public class WebPageEventController {
    private final WebPageEventService webPageEventService;
    private final WebPageMemberService webPageMemberService;

    public WebPageEventController(WebPageEventService webPageEventService, WebPageMemberService webPageMemberService) {
        this.webPageEventService = webPageEventService;
        this.webPageMemberService = webPageMemberService;
    }

    @RequestMapping(value = "events", method = RequestMethod.GET)
    public ModelAndView getEventsView() {
        ModelAndView modelAndView = new ModelAndView("events");
        modelAndView.addObject("events", webPageEventService.getAllEvents());
        modelAndView.addObject("event", new Event());
        return modelAndView;
    }

    @RequestMapping(value = "events", method = RequestMethod.POST)
    public ModelAndView createEvent(@ModelAttribute Event event) {
        webPageEventService.saveNewEvent(event);
        ModelAndView modelAndView = new ModelAndView("events");
        modelAndView.addObject("events", webPageEventService.getAllEvents());
        return modelAndView;
    }


    @RequestMapping(method = RequestMethod.GET, value = "events/{id}")
    public ModelAndView getEventView(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("event");
        Event event = webPageEventService.getEventById(id);
        List<Member> allVipMembers = webPageMemberService.getMembersByRole(VIP);
        List<Member> nonVipEventMembers = event.getMembers().stream()
                .filter(member -> member.getPermissions().stream()
                        .anyMatch(permission -> permission.getRole().equals(NON_VIP)))
                .collect(Collectors.toList());
        modelAndView.addObject("event", event);
        modelAndView.addObject("non_vip_event_members", nonVipEventMembers);
        modelAndView.addObject("all_vip_members", allVipMembers);
        return modelAndView;
    }
}
