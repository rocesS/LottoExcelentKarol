package pl.lotto.numberreceiver;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

class DrawDateGenerator {

    /*
    private final Clock clock;

    DrawDateGenerator(Clock clock) {
        this.clock = clock;
    }
     */

    LocalDateTime date;

    DrawDateGenerator(LocalDateTime date) {
        this.date = date;
    }
    LocalDateTime generateDrawDate() {
        //LocalDateTime currentTime = LocalDateTime.now();
        return date.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)).withHour(12).withMinute(0).withSecond(0).withNano(0);
    }
}
