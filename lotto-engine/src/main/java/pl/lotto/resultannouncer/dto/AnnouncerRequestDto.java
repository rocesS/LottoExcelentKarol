package pl.lotto.resultannouncer.dto;

import jakarta.validation.constraints.Pattern;

public record AnnouncerRequestDto(@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}$") String id) {
}
