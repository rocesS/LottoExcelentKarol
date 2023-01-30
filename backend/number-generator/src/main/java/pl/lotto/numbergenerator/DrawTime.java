package pl.lotto.numbergenerator;

enum DrawTime {
    HOURS(12), MINUTES(0), SECONDS(0), NANO(0);

    final int value;

    DrawTime(int value) {
        this.value = value;
    }
}
