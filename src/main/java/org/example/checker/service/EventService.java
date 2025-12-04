package org.example.checker.service;

import org.example.checker.dto.request.EventCreateRequest;
import org.example.checker.dto.response.EventResponse;
import org.example.checker.dto.response.EventResponseFull;
import org.example.checker.model.Event;
import org.example.checker.repository.EventRepository;
import org.example.checker.service.mapper.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EventService {
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;
    private final LocalFileStorageService localFileStorageService;

    @Autowired
    public EventService(EventMapper eventMapper, EventRepository eventRepository, LocalFileStorageService localFileStorageService) {
        this.eventMapper = eventMapper;
        this.eventRepository = eventRepository;
        this.localFileStorageService = localFileStorageService;
    }

    public Event save(Event event){
        return eventRepository.save(event);
    }

    public Event getById(Long id){
        return eventRepository.findById(id).orElseThrow(()-> new RuntimeException("Событие не найдено: " + id));
    }

    public EventResponse createEvent(EventCreateRequest eventCreateRequest, MultipartFile file) {
        Event event = eventMapper.toEntity(eventCreateRequest);

        localFileStorageService.save(file);
        event.setCoverImageUrl(file.getOriginalFilename());

        Event savedEvent = save(event);
        return eventMapper.toDto(savedEvent);
    }

    public EventResponse findEventById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Событие не найдено: " + id));
        return eventMapper.toDto(event);
    }

    public EventResponseFull findFullEventById(Long id) {
        Event event = eventRepository.findByIdWithSessionsAndVenue(id).orElseThrow(() ->
                new RuntimeException("Событие не найдено: " + id));
        return eventMapper.toFullDto(event);
    }

    public List<EventResponse> findAllEvents() {
        return eventRepository.findAll().stream().map(eventMapper::toDto).collect(Collectors.toList());
    }
}
