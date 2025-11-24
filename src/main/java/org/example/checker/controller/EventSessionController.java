package org.example.checker.controller;

import org.example.checker.dto.request.EventSessionCreateRequest;
import org.example.checker.service.EventSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event_session")
public class EventSessionController {

    private final EventSessionService eventSessionService;

    @Autowired
    public EventSessionController(EventSessionService eventSessionService) {
        this.eventSessionService = eventSessionService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEventSession(@RequestBody EventSessionCreateRequest eventSessionCreateRequest) {
        eventSessionService.createEventSession(eventSessionCreateRequest);
    }
}
