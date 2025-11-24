package org.example.checker.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {
    private Long id;
    private String title;
    private String subtitle;
    private String shortDescription;
    private String fullDescription;
    private String category;
    private Integer ageRestriction;
    private String coverImageUrl;
    private String organizerName;
    private String organizerDescription;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
