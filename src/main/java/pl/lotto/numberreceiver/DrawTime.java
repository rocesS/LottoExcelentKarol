package pl.lotto.numberreceiver;

import java.time.DayOfWeek;

enum DrawTime {
    DAY(DayOfWeek.SATURDAY), HOURS(12), MINUTES(0), SECONDS(0), NANO(0);

    private DayOfWeek dayOfWeek;
    private int value;

    DrawTime(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    DrawTime(int value) {
        this.value = value;
    }

    int getValue() {
        return value;
    }

    DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}