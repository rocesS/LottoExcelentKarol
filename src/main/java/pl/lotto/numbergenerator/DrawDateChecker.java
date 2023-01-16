package pl.lotto.numbergenerator;

import java.time.Clock;
import java.time.LocalDateTime;

class DrawDateChecker {

    Clock clock;

    DrawDateChecker(Clock clock) {
        this.clock = clock;
    }

    boolean isAfterDraw(LocalDateTime drawDate) {
        return LocalDateTime.now(clock).isAfter(drawDate);
    }
}
