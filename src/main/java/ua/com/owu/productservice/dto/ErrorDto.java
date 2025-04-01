package ua.com.owu.productservice.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.Map;

@Builder
public record ErrorDto(
        String message,
        Instant timestamp,
        Map<String, String> details
) {
}
