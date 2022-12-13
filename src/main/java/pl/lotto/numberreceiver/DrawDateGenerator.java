package pl.lotto.numberreceiver;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class DrawDateGenerator {

    LocalDateTime generateDrawDate() {
        LocalDateTime currentTime = LocalDateTime.now();
        return currentTime.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)).withHour(12);
    }
}
