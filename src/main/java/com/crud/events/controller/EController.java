package com.crud.events.controller;

import com.crud.events.service.WebPageEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EController {
    private final WebPageEventService webPageEventService;

    @Autowired
    public EController(WebPageEventService webPageEventService) {
        this.webPageEventService = webPageEventService;
    }

    @GetMapping("/events")
    public String showAllEvents(Model model) {
        model.addAttribute("events", webPageEventService.getAllEvents());
        return "all-events";
    }

    @GetMapping("/events/{id}/delete")
    public String deleteEvent(@PathVariable("id") long id) {
        webPageEventService.removeEventById(id);
        return "redirect:/events";
    }
}
