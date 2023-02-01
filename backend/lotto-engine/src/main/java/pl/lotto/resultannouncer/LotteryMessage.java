package pl.lotto.resultannouncer;

public enum LotteryMessage {
    INVALID_ID("Invalid ID"), WIN("You won!"), LOSE("You lost!"), NO_DRAW("The draw has not yet taken place");

    public final String message;

    LotteryMessage(String message) {
        this.message = message;
    }
}
