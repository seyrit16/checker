package org.example.checker.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class VenueCreateRequest {
    private String name;
    private String address;
    private String phone;
}
