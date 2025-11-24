package org.example.checker.dto.request;

import jakarta.persistence.*;
import lombok.*;
import org.example.checker.model.Event;
import org.example.checker.model.Venue;
import org.example.checker.model.invariants.EventSessionStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EventSessionCreateRequest {
    private Long eventId;
    private Venue venue;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Boolean is_online;
    private BigDecimal ticketMinPrice;
    private BigDecimal ticketMaxPrice;
    private String currency;
    private String ticketUrl; // Прямая ссылка на страницу покупки билетов для конкретного сеанса.
    private String ticketServiceName; // Название билетного сервиса, через который осуществляется продажа.

}
