package org.example.checker.repository;

import org.example.checker.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EventRepository extends CrudRepository<Event,Long> {
    @Query("""
        SELECT DISTINCT e FROM Event e
        LEFT JOIN FETCH e.eventSessions es
        LEFT JOIN FETCH es.venue
        WHERE e.id = :eventId
        """)
    Optional<Event> findByIdWithSessionsAndVenue(@Param("eventId") Long eventId);
}
