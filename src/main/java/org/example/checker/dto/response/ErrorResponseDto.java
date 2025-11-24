package org.example.checker.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ErrorResponseDto {
    private int status;
    private String error;
    private List<String> errors;
    private String message;
    private String timestamp;
}
