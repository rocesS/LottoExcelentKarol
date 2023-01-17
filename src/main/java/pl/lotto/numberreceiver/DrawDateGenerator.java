package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;


class DrawDateGenerator {

    LocalDateTime generateDrawDate(LocalDateTime currentDate) {
        return currentDate.with(TemporalAdjusters.next(DrawTime.DAY.getDayOfWeek()))
                .withHour(DrawTime.HOURS.getValue()).withMinute(DrawTime.MINUTES.getValue())
                .withSecond(DrawTime.SECONDS.getValue()).withNano(DrawTime.NANO.getValue());
    }
}
