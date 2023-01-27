package pl.lotto.resultannouncer.dto;

import jakarta.validation.constraints.Pattern;

public class AnnouncerRequestDto {
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}$")
    public String id;

    public AnnouncerRequestDto() {
    }

    public AnnouncerRequestDto(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
