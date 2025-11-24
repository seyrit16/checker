package org.example.checker.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EventCreateRequest {
    private String title;
    private String subtitle;
    private String shortDescription;
    private String fullDescription;
    private String category;
    private String organizerName;
    private String organizerDescription;
    private Integer ageRestriction;
}
