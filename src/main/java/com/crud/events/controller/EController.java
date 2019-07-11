package com.crud.events.controller;

import com.crud.events.domain.Event;
import com.crud.events.domain.Member;
import com.crud.events.domain.Role;
import com.crud.events.service.WebPageEventService;
import com.crud.events.service.WebPageMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class EController {
    private final WebPageEventService webPageEventService;
    private final WebPageMemberService webPageMemberService;

    @Autowired
    public EController(WebPageEventService webPageEventService, WebPageMemberService webPageMemberService) {
        this.webPageEventService = webPageEventService;
        this.webPageMemberService = webPageMemberService;
    }

    @GetMapping("/events")
    public String showAllEvents(Model model) {
        model.addAttribute("events", webPageEventService.getAllEvents());
        return "all-events";
    }

    @GetMapping("/events/new")
    public String showCreateEventForm(Event event, Model model) {
        List<Member> vips = webPageMemberService.getMembersByRole(Role.VIP);
        event.getMembers().addAll(vips);
        model.addAttribute("event", event);
        model.addAttribute("vips", vips);
        model.addAttribute("nonVips", webPageMemberService.getMembersByRole(Role.NON_VIP));
        return "add-event";
    }

    @GetMapping("/events/{id}/delete")
    public String deleteEvent(@PathVariable("id") long id) {
        webPageEventService.removeEventById(id);
        return "redirect:/events";
    }
}
