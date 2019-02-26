package com.crud.events.controller;

import com.crud.events.service.WebPageEventService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("v1/front/")
public class WebPageEventController {
    private final WebPageEventService webPageEventService;

    public WebPageEventController(WebPageEventService webPageEventService) {
        this.webPageEventService = webPageEventService;
    }

    @RequestMapping(value = "events", method = RequestMethod.GET)
    public ModelAndView getEventsView() {
        ModelAndView modelAndView = new ModelAndView("events");
        modelAndView.addObject("events", webPageEventService.getAllEvents());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "events/{id}")
    public ModelAndView getEventView(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("event");
        modelAndView.addObject("event", webPageEventService.getEventById(id));
        return modelAndView;
    }
}