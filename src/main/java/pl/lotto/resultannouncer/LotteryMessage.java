package pl.lotto.resultannouncer;

public enum LotteryMessage {
    INVALID("invalid"), WIN("you won!"), LOSE("you lost!");

    public final String message;

    LotteryMessage(String message) {
        this.message = message;
    }
}
