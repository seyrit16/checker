package org.example.checker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.checker.model.invariants.EventSessionStatus;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event_session")
public class EventSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    private Venue venue;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    @Column(columnDefinition = "varchar(255) default 'false'")
    private Boolean is_online;

    private BigDecimal ticketMinPrice;

    private BigDecimal ticketMaxPrice;

    @Column(columnDefinition = "varchar(255) default 'RUB'")
    private String currency;

    private String ticketUrl; // Прямая ссылка на страницу покупки билетов для конкретного сеанса.

    private String ticketServiceName; // Название билетного сервиса, через который осуществляется продажа.

    private EventSessionStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
