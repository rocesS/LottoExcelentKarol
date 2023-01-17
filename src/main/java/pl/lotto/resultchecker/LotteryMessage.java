package pl.lotto.resultchecker;

enum LotteryMessage {
    INVALID_ID("invalid id"), WIN("you won!"), LOSE("you lost!"), NO_DRAW("the draw has not yet taken place");

    final String message;

    LotteryMessage(String message) {
        this.message = message;
    }
}
