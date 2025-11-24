package org.example.checker.service;

import org.example.checker.dto.request.EventSessionCreateRequest;
import org.example.checker.model.EventSession;
import org.example.checker.repository.EventSessionRepository;
import org.example.checker.service.mapper.EventSessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventSessionService {

    private final EventService eventService;
    private final EventSessionMapper eventSessionMapper;

    private final EventSessionRepository eventSessionRepository;

    @Autowired
    public EventSessionService(EventService eventService, EventSessionMapper eventSessionMapper, EventSessionRepository eventSessionRepository) {
        this.eventService = eventService;
        this.eventSessionMapper = eventSessionMapper;
        this.eventSessionRepository = eventSessionRepository;
    }

    public void save(EventSession eventSession) {
        eventSessionRepository.save(eventSession);
    }

    public void createEventSession(EventSessionCreateRequest eventSessionCreateRequest) {
        EventSession eventSession = eventSessionMapper.toEntity(eventSessionCreateRequest);
        eventSession.setEvent(eventService.getById(eventSessionCreateRequest.getEventId()));

        save(eventSession);
    }
}
