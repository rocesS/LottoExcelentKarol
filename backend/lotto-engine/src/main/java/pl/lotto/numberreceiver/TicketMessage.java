package pl.lotto.numberreceiver;

public enum TicketMessage {
    VALID("valid"), INVALID("invalid");

    public final String message;

    TicketMessage(String message) {
        this.message = message;
    }
}
