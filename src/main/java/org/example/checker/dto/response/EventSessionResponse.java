package org.example.checker.dto.response;

import lombok.*;
import org.example.checker.dto.VenueDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventSessionResponse {
    private Long id;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Boolean is_online;
    private BigDecimal ticketMinPrice;
    private BigDecimal ticketMaxPrice;
    private String currency;
    private String ticketUrl; // Прямая ссылка на страницу покупки билетов для конкретного сеанса.
    private String ticketServiceName; // Название билетного сервиса, через который осуществляется продажа.
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private VenueDto venue;
}
