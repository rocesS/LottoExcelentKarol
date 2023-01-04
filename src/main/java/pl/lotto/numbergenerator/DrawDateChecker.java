package pl.lotto.numbergenerator;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

class DrawDateChecker {
    LocalDateTime date;

    DrawDateChecker(LocalDateTime date) {
        this.date = date;
    }

    boolean isDateBeforeDraw(LocalDateTime drawDate) {
        return date.isBefore(drawDate);
    }

    boolean isDateAfterDraw(LocalDateTime drawDate) {
        return date.isAfter(drawDate);
    }
}
