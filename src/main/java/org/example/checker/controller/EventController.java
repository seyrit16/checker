package org.example.checker.controller;

import org.example.checker.dto.request.EventCreateRequest;
import org.example.checker.dto.response.EventResponse;
import org.example.checker.dto.response.EventResponseFull;
import org.example.checker.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EventResponse> createEvent(@RequestPart("event") EventCreateRequest event,
                            @RequestPart("eventCover") MultipartFile eventCover) {
        EventResponse eventResponse = eventService.createEvent(event, eventCover);
        return new ResponseEntity<>(eventResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{event_id}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable Long event_id) {
        EventResponse eventResponse = eventService.findEventById(event_id);
        return ResponseEntity.ok(eventResponse);
    }

    @GetMapping("/full/{event_id}")
    public ResponseEntity<EventResponseFull> getEventFull(@PathVariable Long event_id) {
        EventResponseFull eventResponseFull = eventService.findFullEventById(event_id);
        return ResponseEntity.ok(eventResponseFull);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EventResponse>> getAllEvents() {
        return ResponseEntity.ok(eventService.findAllEvents());
    }
}
