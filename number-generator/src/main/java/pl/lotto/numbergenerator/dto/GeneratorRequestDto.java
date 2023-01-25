package pl.lotto.numbergenerator.dto;

import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
//@Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])[/\\-](0?[1-9]|1[012])[/\\-]2\\d[2-9]\\d$") String date
// @DateTimeFormat(pattern="yyyy-mm-dd")
public record GeneratorRequestDto(String date) {
}
