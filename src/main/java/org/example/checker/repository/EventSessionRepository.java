package org.example.checker.repository;

import org.example.checker.model.EventSession;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventSessionRepository extends CrudRepository<EventSession,Long> {
    @Query("SELECT es FROM EventSession es JOIN FETCH es.venue WHERE es.event.id = :eventId")
    Optional<List<EventSession>> findAllByEventIdWithVenue(@Param("eventId") Long eventId);
}
