package pl.lotto.numberreceiver;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;


class DrawDateGenerator {

    LocalDateTime date;

    DrawDateGenerator(LocalDateTime date) {
        this.date = date;
    }

    LocalDateTime generateDrawDate() {
        return date.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)).withHour(12).withMinute(0).withSecond(0).withNano(0);
    }
}
