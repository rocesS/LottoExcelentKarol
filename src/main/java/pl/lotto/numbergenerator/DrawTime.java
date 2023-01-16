package pl.lotto.numbergenerator;

enum DrawTime {
    HOURS(12), MINUTES(0), SECONDS(0);

    private int value;
    DrawTime(int value) {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}
