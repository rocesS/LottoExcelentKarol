package pl.lotto.resultannouncer;

public enum LotteryMessage {
    INVALID_ID("invalid id"), WIN("you won!"), LOSE("you lost!"), NO_DRAW("the draw has not yet taken place");

    public final String message;

    LotteryMessage(String message) {
        this.message = message;
    }
}
