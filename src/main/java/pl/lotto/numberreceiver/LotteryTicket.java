package pl.lotto.numberreceiver;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Document(collection = "lotteryTickets")
class LotteryTicket {

    @Id
    private final UUID id;
    private final List<Integer> numbers;
    private final LocalDateTime drawDate;
    private final String message;

    LotteryTicket(UUID id, List<Integer> numbers, LocalDateTime drawDate, String message) {
        this.id = id;
        this.numbers = numbers;
        this.drawDate = drawDate;
        this.message = message;
    }

    UUID getId() {
        return id;
    }

    List<Integer> getNumbers() {
        return numbers;
    }

    LocalDateTime getDrawDate() {
        return drawDate;
    }

    String getMessage() {
        return message;
    }
}

